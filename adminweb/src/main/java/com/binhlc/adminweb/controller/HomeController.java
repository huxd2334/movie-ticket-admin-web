package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.repo.DetailTransactionRepo;
import com.binhlc.adminweb.repo.MovieRepo;
import com.binhlc.adminweb.repo.RoomRepo;
import com.binhlc.adminweb.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private DetailTransactionRepo detailTransactionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    @GetMapping("/")
    public String home(Model model) {
        long totalMovies = movieRepo.count();
        model.addAttribute("totalMovies", totalMovies);
        long totalTransactions = detailTransactionRepo.count();
        model.addAttribute("totalTrans", totalTransactions);
        long totalUsers = userRepo.count();
        model.addAttribute("totalUsers", totalUsers);
        long totalRooms = roomRepo.count();
        model.addAttribute("totalRooms", totalRooms);
        return "index";

    }
}
