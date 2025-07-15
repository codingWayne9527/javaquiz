package com.example.javaquiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {
    private final QuestionService service;

    public QuizController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showQuiz(Model model) {
        List<Question> questions = service.getQuestions(5);
        model.addAttribute("questions", questions);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String,String> formData, Model model) {
        List<Question> questions = service.getQuestions(5);
        int score = 0;
        Map<Long, Integer> answers = new HashMap<>();
        for (Question q : questions) {
            String answer = formData.get("q" + q.getId());
            if (answer != null) {
                int ans = Integer.parseInt(answer);
                answers.put(q.getId(), ans);
                if (ans == q.getCorrect()) {
                    score++;
                }
            }
        }
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        model.addAttribute("score", score);
        return "quiz";
    }
}
