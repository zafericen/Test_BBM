package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.question.Answer;
import me.nullpointerexception.backend.model.question.Question;
import me.nullpointerexception.backend.pojo.AnswerInfo;
import me.nullpointerexception.backend.pojo.QuestionInfo;
import me.nullpointerexception.backend.repository.AnswerRepository;
import me.nullpointerexception.backend.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class QuestionController {

    AnswerRepository answerRepository;
    QuestionRepository questionRepository;

    @GetMapping(value = "user/question/{productId}")
    public ResponseEntity<Object> getQuestions(@PathVariable String productId){
        List<Question> questions = questionRepository.getQuestions(productId);
        if (questions == null) {
            return new ResponseEntity<>("Questions not found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    @PostMapping(value = "user/addQuestion")
    public ResponseEntity<Object> addQuestion(QuestionInfo questionInfo){
        Question question = new Question(UUID.randomUUID(),questionInfo.userID(),questionInfo.productID(),System.currentTimeMillis(),questionInfo.question());
        try {
            questionRepository.addQuestion(question);
            return new ResponseEntity<>("Question added successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add the question.", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "user/deleteQuestion/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable String id){
        try {
            questionRepository.deleteQuestion(id);
            return new ResponseEntity<>("Question deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the question.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "user/answer/{questionID}")
    public ResponseEntity<Object> getAnswer(@PathVariable String questionID){
        Answer answer = answerRepository.getAnswer(questionID);
        if (answer == null) {
            return new ResponseEntity<>("Answer not found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping(value = "user/addAnswer")
    public ResponseEntity<Object> addAnswer(AnswerInfo answerInfo){
        Answer answer = new Answer(UUID.randomUUID(),answerInfo.ProductID(),answerInfo.QuestionID(),System.currentTimeMillis(),answerInfo.Answer());
        try {
            answerRepository.addAnswer(answer);
            return new ResponseEntity<>("Answer added successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add the answer.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "user/deleteAnswer/{id}")
    public ResponseEntity<Object> deleteAnswer(@PathVariable String id){
        try {
            answerRepository.deleteAnswer(id);
            return new ResponseEntity<>("Answer deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the answer.", HttpStatus.BAD_REQUEST);
        }
    }
}
