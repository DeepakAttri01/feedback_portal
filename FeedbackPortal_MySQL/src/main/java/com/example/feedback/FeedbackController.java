package com.example.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackRepository repo;

    @GetMapping
    public List<Feedback> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Feedback getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/course/{courseId}")
    public List<Feedback> getByCourse(@PathVariable String courseId) {
        return repo.findByCourseId(courseId);
    }

    @PostMapping
    public Feedback create(@RequestBody Feedback feedback) {
        return repo.save(feedback);
    }

    @PutMapping("/{id}")
    public Feedback update(@PathVariable Long id, @RequestBody Feedback newFeedback) {
        return repo.findById(id)
                .map(f -> {
                    f.setCourseId(newFeedback.getCourseId());
                    f.setRating(newFeedback.getRating());
                    f.setComment(newFeedback.getComment());
                    return repo.save(f);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
