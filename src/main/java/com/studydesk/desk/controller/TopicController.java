package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Category;
import com.studydesk.desk.entity.Topic;
import com.studydesk.desk.persistence.CategoryRepository;
import com.studydesk.desk.persistence.TopicRepository;
import com.studydesk.desk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
        modelMap.addAttribute("id", id);

        ArrayList<Topic> topicsWithImage = imageService
                .getBase64String((ArrayList<Topic>) topicRepository.findAll());
        modelMap.addAttribute("list", topicsWithImage);
        return new ModelAndView("overview");
    }

    @GetMapping("/showTopicAddForm")
    public ModelAndView showAddForm(@RequestParam("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("headline", "Add Topic");
        modelMap.addAttribute("addElement", "Topic");
        modelMap.addAttribute("title", "Add Topic");
        modelMap.addAttribute("action", "addTopic?categoryId=" + id);
        return new ModelAndView("add-form", "element", new Topic());
    }

    @PostMapping("/addTopic")
    public ModelAndView addTopic(@RequestParam("categoryId") Integer categoryId,
                                 @RequestParam("image") MultipartFile file,
                                 @ModelAttribute("element") Topic topic, BindingResult result) {
        try {
            topic.setImage(file.getBytes());
            topic.setCategory(categoryRepository.getById(categoryId));
            topicRepository.saveAndFlush(topic);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new ModelAndView("redirect:/topics?id=" + categoryId);
    }
}
