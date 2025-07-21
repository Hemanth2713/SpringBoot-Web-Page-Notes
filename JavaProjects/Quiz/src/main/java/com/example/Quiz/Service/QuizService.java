package com.example.Quiz.Service;

import com.example.Quiz.Dao.QuestionDao;
import com.example.Quiz.Dao.QuizDao;
import com.example.Quiz.Model.Question;
import com.example.Quiz.Model.QuestionWrapper;
import com.example.Quiz.Model.Quiz;
import com.example.Quiz.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions=questionDao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);//Here the quiz object actually has the questions.
        //We have to convert those questions into Wrapper
        List<Question> questionsFromDB=quiz.get().getQuestions();//When we use optional first we need to use get
                                                // the object by using get() and then we can say getQuestions().

        //We have to manually convert each question into a Wrapper
        List<QuestionWrapper> questionsForUSer=new ArrayList<>();
        for (Question q:questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUSer.add(qw);
        }

        return new ResponseEntity<>(questionsForUSer,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();//findById will give the optional data. So we want the data so we can say get
                                            //So when we use get you don't have to put optional here
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for (Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
