package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.dto.RoomDTO;
import com.binhlc.adminweb.entity.RoomDetailEntity;
import com.binhlc.adminweb.entity.RoomEntity;
import com.binhlc.adminweb.repo.RoomDetailRepo;
import com.binhlc.adminweb.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RoomDetailRepo roomDetailRepo;
    @PostMapping("/add-room")
    public String addRoom(@ModelAttribute RoomDTO roomDTO, BindingResult result){
        if(result.hasErrors()){
            return "add-room";
        }
        RoomEntity room = new RoomEntity();
        room.setName(roomDTO.getName());
        room.setNumCol(roomDTO.getNumCol());
        room.setNumRow(roomDTO.getNumRow());
        int index = 0;
        for(int i=0; i<room.getNumRow(); i++){
            for(int j=0; j<room.getNumCol(); j++){
                index++;
                RoomDetailEntity detailEntity = new RoomDetailEntity();
                detailEntity.getRoww(i+1);
                detailEntity.getCol(j+1);
                detailEntity.getPosition(index);
                detailEntity.setRoom(room.getIdRoom());
                roomDetailRepo.save(detailEntity);
            }
        }
        roomRepo.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/add-room.html")
    public String showAddRoom(Model model){
        RoomDTO roomDTO = new RoomDTO();
        model.addAttribute("roomDTO", roomDTO);
        return "add-room";
    }


    @GetMapping("")
    public String showRoom(Model model) {
        List<RoomEntity> list = roomRepo.findAll();
        model.addAttribute("roomList", list);
        if(!list.isEmpty())
            model.addAttribute("room", list.get(0));
        return "rooms";
    }
}
