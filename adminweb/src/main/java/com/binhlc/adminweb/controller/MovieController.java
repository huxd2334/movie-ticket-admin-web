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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.List;

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


    @GetMapping("")
    public String showAllMovies(Model model) {
        List<MovieEntity> list = movieRepo.findAll();
        model.addAttribute("movieList", list);
        return "movies";
    }

}
