package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Category;
import com.studydesk.desk.persistence.CategoryRepository;
import com.studydesk.desk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping("/")
    public ModelAndView getHome(@PathVariable(required = false) Integer elementId, ModelMap modelMap) {
        modelMap.addAttribute("linkText", "See Topics");
        modelMap.addAttribute("headline", "Categories");
        modelMap.addAttribute("addElement", "Category");
        if (elementId != null && elementId > 0) {
            modelMap.addAttribute("elementId", elementId);
        }
        ArrayList<Category> categories = imageService
                .getBase64String((ArrayList<Category>) categoryRepository.findAll());
        modelMap.addAttribute("list", categories);
        return new ModelAndView("overview");
    }

    @GetMapping("/showAddForm")
    public ModelAndView showAddForm(ModelMap modelMap) {
        modelMap.addAttribute("headline", "Add Category");
        modelMap.addAttribute("addElement", "Category");
        modelMap.addAttribute("title", "Add Category");
        return new ModelAndView("add-form", "element", new Category());
    }


}