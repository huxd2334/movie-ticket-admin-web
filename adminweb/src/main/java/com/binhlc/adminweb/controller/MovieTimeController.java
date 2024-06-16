package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.dto.MovieTimeDTO;
import com.binhlc.adminweb.entity.*;
import com.binhlc.adminweb.repo.*;
import com.binhlc.adminweb.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/movies/{idMovie}/movie-times/")
public class MovieTimeController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Time.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                try {
                    setValue(new Time(format.parse(text).getTime()));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                if (getValue() != null) {
                    Time time = (Time) getValue();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    return format.format(time);
                } else {
                    return "";
                }
            }
        });
    }

    @Autowired
    private RoomDetailRepo roomDetailRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private MovieTimeRepo movieTimeRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private RoomRepo roomRepo;
    @GetMapping("/add-movie-time.html")
    public String showAddMovieTime(Model model, @PathVariable("idMovie") int idMovie){
        Optional<MovieEntity> movieEntity = movieRepo.findById(idMovie);
        if (movieEntity.isPresent()) {
            model.addAttribute("movie", movieEntity.get());
        } else {
            return "redirect:/movies";
        }
        List<RoomEntity> roomList = roomRepo.findAll();
        model.addAttribute("roomList", roomList);
        MovieTimeDTO movieTimeDTO = new MovieTimeDTO();
        model.addAttribute("movieTimeDTO", movieTimeDTO);// Assuming categoryRepo is your repository for categories>
        return "add-movie-time";
    }

    @PostMapping("/add-movie-time")
    public String addMovieTime(@ModelAttribute MovieTimeDTO movieTimeDTO, BindingResult result, @PathVariable("idMovie") int idMovie, Model model){
        Integer dateStart = CommonUtils.convertDateToYYYYmmDD(movieTimeDTO.getDateStart());
        Integer timeStart = CommonUtils.convertTimeToHHmm(movieTimeDTO.getTimeStart());

        // Check if a MovieTimeEntity with the same dateStart and timeStart already exists
        if (movieTimeRepo.existsByDateStartAndTimeStart(dateStart, timeStart)) {
            result.rejectValue("timeStart", "error.movieTimeDTO", "A movie time with the same start date and time already exists.");
        }

        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "add-movie-time";
        }
        MovieTimeEntity entity = new MovieTimeEntity();
        entity.setIdMovie(idMovie);
        entity.setIdRoom(movieTimeDTO.getIdRoom());
        entity.setDateStart(CommonUtils.convertDateToYYYYmmDD(movieTimeDTO.getDateStart()));
        entity.setTimeStart(CommonUtils.convertTimeToHHmm(movieTimeDTO.getTimeStart()));
        entity.setPrice(movieTimeDTO.getPrice());
        entity.setCreatedBy(1);
        entity.setUpdatedBy(1);
        entity.setTimeCreate(new java.sql.Timestamp(System.currentTimeMillis()));
        entity.setTimeUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        movieTimeRepo.save(entity);
        List<RoomDetailEntity> roomDetailList = roomDetailRepo.findAllByRoom(entity.getIdRoom());
        List<TicketEntity> ticketList = new ArrayList<>();
        for(RoomDetailEntity temp: roomDetailList){
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setIdMovieTime(entity.getId());
            ticketEntity.setIdRoomDetail(temp.getId());
            ticketEntity.setStatus(1);
            ticketEntity.setCreateBy(1);
            ticketEntity.setUpdateBy(1);
            ticketEntity.setTimeCreate(new java.sql.Timestamp(System.currentTimeMillis()));
            ticketEntity.setTimeUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
            if(temp.getType()==1)
                ticketEntity.setPrice(entity.getPrice());
            else if(temp.getType()==2)
                ticketEntity.setPrice(entity.getPrice()*2);
            else
                ticketEntity.setPrice(entity.getPrice());
            ticketList.add(ticketEntity);
        }
        ticketRepo.saveAll(ticketList);
        return "redirect:/movies/" + idMovie + "/movie-times/";
    }

    private Map<Integer, String> createRoomMap(List<RoomEntity> roomList) {
        Map<Integer, String> roomMap = new HashMap<>();
        for (RoomEntity roomEntity : roomList) {
            roomMap.put(roomEntity.getIdRoom(), roomEntity.getName());
        }
        return roomMap;
    }

    @GetMapping("/")
    public String getMovieTimeByIdMovie(@PathVariable("idMovie") int idMovie, Model model) {
        List<RoomEntity> roomList = roomRepo.findAll();
        model.addAttribute("roomList", roomList);

        Optional<MovieEntity> movieEntity = movieRepo.findById(idMovie);
        if (movieEntity.isPresent()) {
            model.addAttribute("movie", movieEntity.get());
        } else {
            return "redirect:/movies";
        }

        List<MovieTimeEntity> movieTimeList = movieTimeRepo.findAllByIdMovie(idMovie);
        model.addAttribute("movieTimeList", movieTimeList);
        Map<Integer, String> roomMap = createRoomMap(roomList);
        model.addAttribute("roomMap", roomMap);
        return "movie-times";
    }

    @GetMapping("/edit-movie-time/{idMT}")
    public String showEditMT(Model model, @PathVariable Integer idMT,  @PathVariable("idMovie") Integer idMovie){
        Optional<MovieTimeEntity> mt = movieTimeRepo.findById(idMT);
        if(mt.isPresent()){
            MovieTimeDTO movieTimeDTO = new MovieTimeDTO();
            movieTimeDTO.setId(idMT);
//            movieTimeDTO.setIdMovie(idMovie); // Assuming this returns an Integer representing a date
//            movieTimeDTO.setIdRoom(mt.get().getIdRoom());
            movieTimeDTO.setDateStart(CommonUtils.convertDateIntToDate(mt.get().getDateStart())); // change Integer format yyyymmdd to java.util.Date
            movieTimeDTO.setTimeStart(CommonUtils.convertTimeIntToTime(mt.get().getTimeStart()));
            movieTimeDTO.setPrice(mt.get().getPrice());
            model.addAttribute("movieTimeDTO", movieTimeDTO);
            return "edit-movie-time";
        }else
            return "redirect:/movies/" + idMovie + "/movie-times/";
    }

    @PostMapping("/edit-movie-time/{idMT}")
    public String editMovieTime( @PathVariable("idMovie") Integer idMovie,@PathVariable Integer idMT, @ModelAttribute MovieTimeDTO movieTimeDTO, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "edit-movie-time";
        }
        MovieTimeEntity entity = movieTimeRepo.findById(idMT).get();
        if(entity==null){
            return "redirect:/movies";
        }else{
//            entity.setIdMovie(idMovie); // Set idMovie from MovieTimeDTO
//            entity.setIdRoom(movieTimeDTO.getIdRoom());
            entity.setDateStart(CommonUtils.convertDateToYYYYmmDD(movieTimeDTO.getDateStart()));
            entity.setTimeStart(CommonUtils.convertTimeToHHmm(movieTimeDTO.getTimeStart()));
            entity.setPrice(movieTimeDTO.getPrice());
            entity.setUpdatedBy(1);
            entity.setTimeUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
            movieTimeRepo.save(entity);
            return "redirect:/movies/"+idMovie+"/movie-times/";
        }
    }

    @RequestMapping(value = "/delete-movie-time/{idMT}", method = RequestMethod.GET)
    public String confirmToDelete( @PathVariable("idMovie") Integer idMovie,@PathVariable("idMT") int idMT, Model model) {
        MovieTimeEntity movieTime = movieTimeRepo.findById(idMT).orElse(null);
        if(movieTime == null) {
            return "redirect:/movies"+idMovie+"/movie-times/";
        }
        model.addAttribute("movieTime", movieTime);
        return "confirm";
    }

    @RequestMapping(value = "/delete-movie-time/{idMT}", method = RequestMethod.POST)
    public String deleteMovieTime( @PathVariable("idMovie") Integer idMovie,@PathVariable("idMT") int idMT, Model model) {
        try{
            List<TicketEntity> ticketList = ticketRepo.findAllByIdMovieTime(idMT);
            MovieTimeEntity movieTime = movieTimeRepo.findById(idMT).orElse(null);
            if(movieTime == null) {
                return "redirect:/movies"+idMovie+"/movie-times/";
            }
            ticketRepo.deleteAll(ticketList);
            movieTimeRepo.deleteById(idMT);
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "Error while deleting movie time");
        }
        return "redirect:/movies/"+idMovie+"/movie-times/";
    }

}
