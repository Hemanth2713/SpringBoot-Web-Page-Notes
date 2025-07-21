//package com.example.Quiz.Controller;
//
//import com.example.Quiz.Model.Question;
//import com.example.Quiz.Service.QuestionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("questions")
//public class QuestionController {
//    @Autowired
//    QuestionService questionService;
//
//    @PostMapping
//    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
//        Question createdQuestion = questionService.addQuestion(question);
//        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Question>> getAllQuestions() {
//        List<Question> QuestionsList = questionService.getAllTheQuestions();
//        return new ResponseEntity<>(QuestionsList, HttpStatus.OK);  // OK status
//    }
//
//    @GetMapping("Id/{id}")
//    public ResponseEntity<Question> getById(@PathVariable int id) {
//        try {
//            Optional<Question> question = questionService.getQuestionById(id);
//            return question.map(q -> new ResponseEntity<>(q, HttpStatus.FOUND))
//                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
//










package com.example.Quiz.Controller;

import com.example.Quiz.Model.Question;
import com.example.Quiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("questions")
@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {

        Question createdQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> QuestionsList=questionService.getAllTheQuestions();
        return new ResponseEntity<>(QuestionsList,HttpStatus.FOUND);
    }

    @GetMapping("Id/{id}")
    public ResponseEntity<Optional<Question>> getById(@PathVariable int id){
        try {
            Optional<Question> question = questionService.getQuestionById(id);
            if(question.isPresent())
                return new ResponseEntity<>(question,HttpStatus.FOUND);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        List<Question> questions=questionService.getQuestionsByCategory(category);
        return new ResponseEntity<>(questions,HttpStatus.FOUND);
    }

    @DeleteMapping("Id/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int questionId){
        try {
            boolean questionDelete=questionService.deleteQuestionById(questionId);
            if(questionDelete){
                return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Question Not Found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("An error occurred while deleting the question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
