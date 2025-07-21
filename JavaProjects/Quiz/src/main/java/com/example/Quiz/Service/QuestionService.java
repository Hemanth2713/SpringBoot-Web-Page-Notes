package com.example.Quiz.Service;

import com.example.Quiz.Dao.QuestionDao;
import com.example.Quiz.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
