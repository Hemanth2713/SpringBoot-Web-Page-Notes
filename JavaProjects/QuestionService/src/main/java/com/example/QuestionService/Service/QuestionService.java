package com.example.QuestionService.Service;

import com.example.QuestionService.Dao.QuestionDao;
import com.example.QuestionService.Model.Question;
import com.example.QuestionService.Model.QuestionWrapper;
import com.example.QuestionService.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public Question addQuestion(Question question) {
       return questionDao.save(question);
    }

    public List<Question> getAllTheQuestions() {
        return questionDao.findAll();
    }

    public Optional<Question> getQuestionById(int id) {
        return questionDao.findById(id);
    }

    public boolean deleteQuestionById(int questionId) {
        Optional<Question> question=questionDao.findById(questionId);
        if(question.isPresent()){
             questionDao.deleteById(questionId);
            return true;
        }
        return false;
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findQuestionByCategory(category);
    }

    public ResponseEntity<List<Integer>> getQeustionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions=questionDao.findRandomQuestionByCategory(categoryName,numQuestions);
        return  new ResponseEntity<>(questions, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        
        for(Integer id: questionIds){
            questions.add(questionDao.findById(id).get());
        }
        
        for (Question question : questions){
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return  new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int right=0;
        for (Response response: responses){
            Question question=questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
