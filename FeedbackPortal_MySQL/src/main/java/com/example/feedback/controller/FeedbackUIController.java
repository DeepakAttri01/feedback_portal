package com.example.feedback.controller;

import com.example.feedback.Feedback;
import com.example.feedback.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeedbackUIController {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @GetMapping("/")
    public String viewForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "add-feedback"; // matches add-feedback.html
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute Feedback feedback) {
        feedbackRepo.save(feedback);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listFeedbacks(Model model) {
        model.addAttribute("feedbackList", feedbackRepo.findAll());
        return "list-feedback";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackRepo.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String editFeedback(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackRepo.findById(id).orElseThrow();
        model.addAttribute("feedback", feedback);
        return "add-feedback";
    }
}
