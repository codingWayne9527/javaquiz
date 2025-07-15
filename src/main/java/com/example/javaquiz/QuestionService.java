package com.example.javaquiz;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> getQuestions(int count) {
        return repository.findAll().stream().limit(count).toList();
    }
}
