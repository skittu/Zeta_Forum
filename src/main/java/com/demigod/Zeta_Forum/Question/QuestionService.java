package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;

    public void addQuestion(QuestionPostedFrontend que,String userId) {

        // storing question in repository
        UUID questionUUID = UUID.randomUUID();
        Date date = new Date();
        Question questionToBeInserted=new Question(
                questionUUID.toString(),
                que.getQuestion().toLowerCase(),
                date,
                date,
                userId);
        questionRepository.save(questionToBeInserted);

        // storing tags in repository




    }

    public List<Question> getAllQuestions() {
        List<Question> ques=new ArrayList<>();
        questionRepository.findAll()
                .forEach(ques::add);
        return ques;
    }

    public List<Question> getAllQuestionsOfThisUser(String userId) {
        List<Question> ques=new ArrayList<>();
        return questionRepository.findAllByUserId(userId);
    }
}
