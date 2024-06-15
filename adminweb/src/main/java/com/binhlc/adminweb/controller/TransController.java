package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.entity.DetailTransactionEntity;
import com.binhlc.adminweb.entity.TransactionEntity;
import com.binhlc.adminweb.repo.DetailTransactionRepo;
import com.binhlc.adminweb.repo.TicketRepo;
import com.binhlc.adminweb.repo.TransactionRepo;
import com.binhlc.adminweb.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trans")
public class TransController {
    @Autowired
    private DetailTransactionRepo detailTransactionRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping
    public String showAllTrans(Model model) {
        try {
            List<TransactionEntity> transList = transactionRepo.findAllByType(2);
            model.addAttribute("transList", transList);

            Map<Integer, Integer> ticketTotal = getTicketTotal(transList);
            model.addAttribute("ticketTotal", ticketTotal);

            Map<Integer, Long> ticketPrice = getTicketTotalPrice(transList);
            model.addAttribute("ticketTotalPrice", ticketPrice);

            Map<Integer, String> userName = getUserName(transList);
            model.addAttribute("userName", userName);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "Error while loading data");
        }
//        model.addAttribute("utils", commonUtils);
        return "trans";
    }

    private Map<Integer, Integer> getTicketTotal(List<TransactionEntity> transList) {
        Map<Integer, Integer> ticketNum = new HashMap<>();
        for (TransactionEntity t : transList) {
            List<DetailTransactionEntity> bookedList = detailTransactionRepo.findAllByIdTransaction(t.getId()); // get id of each ticket in list
            ticketNum.put(t.getId(), bookedList.size());
        }
        return ticketNum;
    }

    private  Map<Integer, Long> getTicketTotalPrice(List<TransactionEntity> transList) {
        Map<Integer, Long> ticketPrice = new HashMap<>();
        for (TransactionEntity t : transList) {
            List<DetailTransactionEntity> bookedList = detailTransactionRepo.findAllByIdTransaction(t.getId()); // get id of each ticket in list
            Long totalPrice = 0L;
            for (DetailTransactionEntity d : bookedList) {
                totalPrice += ticketRepo.findById(d.getIdTicket()).get().getPrice();
            }
            ticketPrice.put(t.getId(), totalPrice);
        }
        return ticketPrice;
    }

    private Map<Integer, String> getUserName(List<TransactionEntity> transList) {
        Map<Integer, String> userName = new HashMap<>();
        for (TransactionEntity t : transList) {
            String firstName = userRepo.findById(t.getIdUser()).get().getName();
            String lastName = userRepo.findById(t.getIdUser()).get().getLastName();
            userName.put(t.getId(), firstName + " " + lastName);
        }
        return userName;
    }
}
