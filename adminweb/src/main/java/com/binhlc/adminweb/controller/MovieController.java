package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.dto.MovieDTO;
import com.binhlc.adminweb.entity.CategoryEntity;
import com.binhlc.adminweb.entity.MovieCategoryEntity;
import com.binhlc.adminweb.entity.MovieEntity;
import com.binhlc.adminweb.repo.CategoryRepo;
import com.binhlc.adminweb.repo.MovieCategoryRepo;
import com.binhlc.adminweb.repo.MovieRepo;
import com.binhlc.adminweb.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MovieCategoryRepo movieCategoryRepo;

    @GetMapping("/add-movie.html")
    public String showAddMovie(Model model) {
        List<CategoryEntity> categoryList = categoryRepo.findAll(); // Assuming categoryRepo is your repository for categories
        model.addAttribute("categoryList", categoryList);
        MovieDTO movieDTO = new MovieDTO();
        model.addAttribute("movieDTO", movieDTO);
        return "add-movie";
    }

    @PostMapping("/add-movie")
    public String addMovie(@ModelAttribute MovieDTO movieDTO, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "add-movie";
        }
        MovieEntity movie = new MovieEntity();
        movie.setName(movieDTO.getName());
        movie.setDirector(movieDTO.getDirector());
        movie.setActor(movieDTO.getActor());
        movie.setStoryLine(movieDTO.getStoryLine());
        movie.setTrailer(movieDTO.getTrailer());
        movie.setReleasedDate(CommonUtils.convertDateToYYYYmmDD(movieDTO.getReleasedDate()));
        movie.setDuration((movieDTO.getDuration()));
        movie.setStatus((movieDTO.getStatus()));
        movie.setLanguage(movieDTO.getLanguage());
        movie.setSubTitle(movieDTO.getSubTitle());
        movie.setCountry(movieDTO.getCountry());
        movie.setCensor((movieDTO.getCensor()));
        movie.setDubbing((movieDTO.getDubbing()));
        movie.setTimeUpdate(new Timestamp(System.currentTimeMillis()));
        movie.setTimeCreate(new Timestamp(System.currentTimeMillis()));
//        movie.setThumb(movieDTO.getThumbnail());
        movie.setView(0);
        movie.setUpdatedBy(1);
        movie.setCreatedBy(1);
        movieRepo.save(movie);
        if (movieDTO.getCategories() != null && !movieDTO.getCategories().isEmpty()) {
            for (Integer categoryId : movieDTO.getCategories()) {
                MovieCategoryEntity movieCategory = new MovieCategoryEntity();
                movieCategory.setIdMovie(movie.getId());
                movieCategory.setIdCategory(categoryId);
                movieCategoryRepo.save(movieCategory);
            }
        }
        return "redirect:/movies";
    }

    @GetMapping("/edit-movie/{id}")
    public String showEditMovie(Model model, @PathVariable Integer id) {
        MovieEntity movie = movieRepo.findById(id).orElse(null);
        if (movie == null) {
            return "redirect:/movies";
        }
        List<CategoryEntity> categoryList = categoryRepo.findAll(); // Assuming categoryRepo is your repository for categories
        model.addAttribute("categoryList", categoryList);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setActor(movie.getActor());
        movieDTO.setStoryLine(movie.getStoryLine());
        movieDTO.setTrailer(movie.getTrailer());
        movieDTO.setReleasedDate(CommonUtils.convertIntToDate(movie.getReleasedDate())); // Now you can pass it to setReleasedDate
        movieDTO.setDuration((movie.getDuration()));
        movieDTO.setStatus((movie.getStatus()));
        movieDTO.setLanguage(movie.getLanguage());
        movieDTO.setSubTitle(movie.getSubTitle());
        movieDTO.setCountry(movie.getCountry());
        movieDTO.setCensor((movie.getCensor()));
        movieDTO.setDubbing((movie.getDubbing()));
//        movieDTO.setThumbnail(movie.getThumbnail());
        List<MovieCategoryEntity> movieCategoryList = movieCategoryRepo.findAllByIdMovie(movie.getId());
        List<Integer> categoryIds = new ArrayList<>();
        for (MovieCategoryEntity movieCategory : movieCategoryList) {
            categoryIds.add(movieCategory.getIdCategory());
        }
//        System.out.println("Loading edit form for movie ID: " + id);
//        System.out.println("MovieDTO ID: " + movieDTO.getId());
        movieDTO.setCategories(categoryIds);
        model.addAttribute("movieDTO", movieDTO);
        return "edit-movie";

    }

    @PostMapping("/edit-movie/{id}")
    public String editMovie(@ModelAttribute MovieDTO movieDTO, BindingResult result, Model model, @PathVariable Integer id) {
        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "edit-movie";
        }

        // fetch the movie
//        MovieEntity movie = new MovieEntity();
        MovieEntity movie = movieRepo.findById(id).orElse(null);
        if (movie == null) {
            // If no such movie exists, redirect to the movies page
            return "redirect:/movies";
        }
//        movie.setId(id);
        movie.setName(movieDTO.getName());
        movie.setDirector(movieDTO.getDirector());
        movie.setActor(movieDTO.getActor());
        movie.setStoryLine(movieDTO.getStoryLine());
        movie.setTrailer(movieDTO.getTrailer());
        movie.setReleasedDate(CommonUtils.convertDateToYYYYmmDD(movieDTO.getReleasedDate()));
        movie.setDuration((movieDTO.getDuration()));
        movie.setStatus((movieDTO.getStatus()));
        movie.setLanguage(movieDTO.getLanguage());
        movie.setSubTitle(movieDTO.getSubTitle());
        movie.setCountry(movieDTO.getCountry());
        movie.setCensor((movieDTO.getCensor()));
        movie.setDubbing((movieDTO.getDubbing()));
        movie.setTimeUpdate(new Timestamp(System.currentTimeMillis()));
//        movie.setThumb(movieDTO.getThumbnail());
//        movie.setView(movie.getView());
        movie.setUpdatedBy(1);
//        movie.setCreatedBy(1);
        movieRepo.save(movie); // save the movie

        // delete first, then set new categories for the movie
        movieCategoryRepo.deleteAllByIdMovie(movie.getId());
        if (movieDTO.getCategories() != null && !movieDTO.getCategories().isEmpty()) {
            for (Integer categoryId : movieDTO.getCategories()) {
                MovieCategoryEntity movieCategory = new MovieCategoryEntity();
                movieCategory.setIdMovie(movie.getId());
                movieCategory.setIdCategory(categoryId);
                movieCategoryRepo.save(movieCategory);
            }
        }
        return "redirect:/movies";
    }


    @GetMapping("")
    public String showAllMovies(Model model) {
        List<MovieEntity> list = movieRepo.findAll();
        Map<Integer, List<CategoryEntity>> movieCategoryMap = createMovieCategory(list); // get all movies with categories using map>
        model.addAttribute("movieList", list);
        model.addAttribute("movieCategories", movieCategoryMap);
        return "movies";
    }

    // get all movies with categories using map
    private Map<Integer, List<CategoryEntity>> createMovieCategory (List<MovieEntity> movies){
        Map<Integer, List<CategoryEntity>> movieCategoryMap = new HashMap<>();
        for (MovieEntity movie : movies) {
            List<CategoryEntity> categories = getCategoriesByMovie(movie);
            movieCategoryMap.put(movie.getId(), categories.isEmpty()? Collections.emptyList(): categories);
        }
        return movieCategoryMap;
    }

    // get categories by movie
    private List<CategoryEntity> getCategoriesByMovie (MovieEntity movie){
        List<CategoryEntity> categories = new ArrayList<>();
        List<MovieCategoryEntity> movieCatList = movieCategoryRepo.findAllByIdMovie(movie.getId());
        for(MovieCategoryEntity entity: movieCatList){
            CategoryEntity category = categoryRepo.findById(entity.getIdCategory()).orElse(null);
            categories.add(category);
        }
        return categories;
    }

}
