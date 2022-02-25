package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Category;
import com.studydesk.desk.entity.Topic;
import com.studydesk.desk.persistence.CategoryRepository;
import com.studydesk.desk.persistence.TopicRepository;
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
        modelMap.addAttribute("isContent", "false");
        modelMap.addAttribute("id", id);

        ArrayList<Topic> topicsWithImage = imageService.getBase64String(topics);
        modelMap.addAttribute("list", topicsWithImage);
        return new ModelAndView("overview");
    }

    @GetMapping("/showTopicAddForm")
    public ModelAndView showAddForm(@RequestParam("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("headline", "Add Topic");
        modelMap.addAttribute("addElement", "Topic");
        modelMap.addAttribute("title", "Add Topic");
        modelMap.addAttribute("action", "addTopic?categoryId=" + id);
        modelMap.addAttribute("name", "");
        modelMap.addAttribute("image", "");
        return new ModelAndView("add-form", "element", new Topic());
    }

    @GetMapping("/showEditTopicForm")
    public ModelAndView showEditTopicForm(@RequestParam("id") Integer id, ModelMap modelMap) {
        final Topic topic = topicRepository.findById(id).get();

        modelMap.addAttribute("headline", "Add Topic");
        modelMap.addAttribute("addElement", "Topic");
        modelMap.addAttribute("title", "Add Topic");
        modelMap.addAttribute("action", "editTopic?id=" + id);
        modelMap.addAttribute("name", topic.getName());
        modelMap.addAttribute("image", topic.getFileName());
        return new ModelAndView("add-form", "element", topic);
    }

    @PostMapping("/editTopic")
    public ModelAndView editTopic(@RequestParam("id") Integer id,
                                  @RequestParam("image") MultipartFile file,
                                  @ModelAttribute("element") Topic topic, BindingResult result) {
        Topic topicToUpdate = topicRepository.findById(id).get();

        try {
            topicToUpdate.setId(id);
            topicToUpdate.setImage(file.getBytes());
            topicToUpdate.setName(topic.getName());
            topicToUpdate.setFileName(file.getOriginalFilename());
            topicRepository.save(topicToUpdate);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return new ModelAndView("redirect:/topics?id=" + topicToUpdate.getCategory().getId());
    }

    @PostMapping("/addTopic")
    public ModelAndView addTopic(@RequestParam("categoryId") Integer categoryId,
                                 @RequestParam("image") MultipartFile file,
                                 @ModelAttribute("element") Topic topic, BindingResult result) {
        try {
            topic.setImage(file.getBytes());
            topic.setCategory(categoryRepository.getById(categoryId));
            topic.setFileName(file.getOriginalFilename());
            topicRepository.saveAndFlush(topic);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new ModelAndView("redirect:/topics?id=" + categoryId);
    }

    @GetMapping("/deleteTopic")
    public ModelAndView deleteTopic(@RequestParam("id") Integer id) {
        final Topic topicToDelete = topicRepository.getById(id);
        final Integer categoryId = topicToDelete.getCategory().getId();
        topicRepository.delete(topicToDelete);

        return new ModelAndView("redirect:/topics?id=" + categoryId);
    }
}
