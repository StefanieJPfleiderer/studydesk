package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Category;
import com.studydesk.desk.entity.Topic;
import com.studydesk.desk.persistence.CategoryRepository;
import com.studydesk.desk.persistence.TopicRepository;
import com.studydesk.desk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping("/topics")
    public ModelAndView getOverview(@RequestParam(value = "id") Integer id, ModelMap modelMap) {
        Category category = categoryRepository.findById(id).get();
        ArrayList<Topic> topics = new ArrayList<>(category.getTopics());

        modelMap.addAttribute("headline", "Topics");
        modelMap.addAttribute("addElement", "Topics");
        modelMap.addAttribute("linkText", "See Content");
        modelMap.addAttribute("isTopic", "true");

        ArrayList<Topic> topicsWithImage = imageService
                .getBase64String((ArrayList<Topic>) topicRepository.findAll());
        modelMap.addAttribute("list", topicsWithImage);
        return new ModelAndView("overview");
    }
}
