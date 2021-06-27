package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Category;
import com.studydesk.desk.persistence.CategoryRepository;
import com.studydesk.desk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
}