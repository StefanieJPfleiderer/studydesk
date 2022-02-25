package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Category;
import com.studydesk.desk.persistence.CategoryRepository;
import com.studydesk.desk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
        modelMap.addAttribute("isTopic", "false");
        modelMap.addAttribute("isContent", "false");
        modelMap.addAttribute("id", "");
        ArrayList<Category> categories = imageService
                .getBase64String((ArrayList<Category>) categoryRepository.findAll());
        modelMap.addAttribute("list", categories);
        return new ModelAndView("overview");
    }

    @GetMapping("/showCategoryAddForm")
    public ModelAndView showAddForm(ModelMap modelMap) {
        modelMap.addAttribute("headline", "Add Category");
        modelMap.addAttribute("addElement", "Category");
        modelMap.addAttribute("title", "Add Category");
        modelMap.addAttribute("action", "addCategory");
        modelMap.addAttribute("title", "");
        modelMap.addAttribute("image", "");
        return new ModelAndView("add-form", "element", new Category());
    }

    @GetMapping("/showEditCategoryForm")
    public ModelAndView showEditCategoryForm(@RequestParam("id") Integer id, ModelMap modelMap) {
        final Category category = categoryRepository.findById(id).get();

        modelMap.addAttribute("headline", "Add Category");
        modelMap.addAttribute("addElement", "Category");
        modelMap.addAttribute("title", "Add Category");
        modelMap.addAttribute("action", "editCategory?id=" + id);
        modelMap.addAttribute("name", category.getName());
        modelMap.addAttribute("image", category.getFileName());

        return new ModelAndView("add-form", "element", category);
    }

    @PostMapping("/addCategory")
    public ModelAndView addCategory(@RequestParam("image") MultipartFile multipartFile,
                                    @ModelAttribute("element") Category category,
                                    BindingResult result, final ModelMap modelMap) {
        try {
            category.setImage(multipartFile.getBytes());
            category.setFileName(multipartFile.getOriginalFilename());
            categoryRepository.saveAndFlush(category);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/editCategory")
    public ModelAndView editCategory(@RequestParam("id") Integer id, @RequestParam("image") MultipartFile multipartFile,
                                     @ModelAttribute("element") Category category,
                                     BindingResult result) {
        try {
            Category updateCategory = categoryRepository.findById(id).get();
            updateCategory.setId(id);
            updateCategory.setName(category.getName());
            updateCategory.setImage(multipartFile.getBytes());
            updateCategory.setFileName(multipartFile.getOriginalFilename());
            categoryRepository.save(updateCategory);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/deleteCategory")
    public ModelAndView deleteCategory(@RequestParam("id") Integer id) {
        final Category categoryToDelete = categoryRepository.getById(id);
        categoryRepository.delete(categoryToDelete);

        return new ModelAndView("redirect:/");
    }

}