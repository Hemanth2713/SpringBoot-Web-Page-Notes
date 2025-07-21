package com.example.QuizService.Controller;

import com.example.QuizService.Model.QuestionWrapper;
import com.example.QuizService.Model.QuizDto;
import com.example.QuizService.Model.Response;
import com.example.QuizService.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumOfQuestions(),quizDto.getTitle());
    }

    @GetMapping("QuizId/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId,@RequestBody List<Response> responses){
        return quizService.calculateResult(quizId,responses);
    }
}
