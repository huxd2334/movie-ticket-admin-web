package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.dto.CategoryDTO;
import com.binhlc.adminweb.entity.CategoryEntity;
import com.binhlc.adminweb.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepo repo;

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "add-category";
        }
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDTO.getName());
        category.setCreatedBy(1);
        category.setUpdatedBy(1);
        category.setTimeCreate(new Timestamp(System.currentTimeMillis()));
        category.setTimeUpdate(new Timestamp(System.currentTimeMillis()));
        repo.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/add-category.html")
    public String showAddCategory(Model model) {
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categoryDTO", categoryDTO);
        return "add-category";
    }

    @GetMapping
    public String showAllCategories(Model model) {
        List<CategoryEntity> list = repo.findAll();
        model.addAttribute("categoryList", list);
        return "categories";
    }

    @RequestMapping(value = "/delete-category/{id}", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable("id") int id, Model model) {
        try {
            repo.deleteById(id);
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "Error while deleting category");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete-category/{id}", method = RequestMethod.GET)
    public String confirmToDelete(@PathVariable("id") int id, Model model) {
        CategoryEntity category = repo.findById(id).orElse(null);
        if(category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "confirm";
    }
}
