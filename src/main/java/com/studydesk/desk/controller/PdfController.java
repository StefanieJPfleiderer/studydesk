package com.studydesk.desk.controller;

import com.studydesk.desk.entity.Pdf;
import com.studydesk.desk.entity.Topic;
import com.studydesk.desk.persistence.PdfRepository;
import com.studydesk.desk.persistence.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class PdfController {

    @Autowired
    private PdfRepository pdfRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/content")
    public ModelAndView getHome(@RequestParam(value = "id") Integer id, ModelMap modelMap) {
        Topic topic = topicRepository.getById(id);
        ArrayList<Pdf> pdfs = new ArrayList<>(topic.getPdfs());

        modelMap.addAttribute("list", pdfs);
        modelMap.addAttribute("id", id);
        return new ModelAndView("content-overview");
    }

    @GetMapping("/showPdfAddForm")
    public ModelAndView showAddForm(@RequestParam("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("headline", "Add PDF");
        modelMap.addAttribute("addElement", "PDF");
        modelMap.addAttribute("title", "Add PDF");
        modelMap.addAttribute("id", id);
        return new ModelAndView("add-pdfLink-form", "element", new Pdf());
    }

    @PostMapping("/addPdf")
    public ModelAndView addTopic(@RequestParam("topicId") Integer topicId,
                                 @ModelAttribute("element") Pdf pdf, BindingResult result) {

        pdf.setTopic(topicRepository.getById(topicId));
        pdfRepository.saveAndFlush(pdf);

        return new ModelAndView("redirect:/content?id=" + topicId);
    }

    @GetMapping("/readMore")
    public ModelAndView readMore(@RequestParam("elementId") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("pdf", pdfRepository.getById(id));
        return new ModelAndView("read-more");
    }

    @GetMapping("/showPdf")
    public ModelAndView showPdf(@RequestParam("elementId") Integer id) {
        Pdf pdf = pdfRepository.getById(id);
        try {
            File file = new File(pdf.getPdfPath());
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return new ModelAndView("redirect:/readMore?elementId=" + pdf.getId());
    }
}
