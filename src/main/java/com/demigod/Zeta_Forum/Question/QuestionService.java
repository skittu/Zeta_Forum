package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;

    public void addQuestions(Long uid,Question que)
    {
        que.setUserId(uid);
        questionRepository.save(que);

    }

    public List<Question> getAllQuestions() {
        List<Question> ques=new ArrayList<>();
        questionRepository.findAll()
                .forEach(ques::add);
        return ques;
    }

    public List<Question> getAllQuestionsOfThisUser(Long uid) {
        List<Question> ques=new ArrayList<>();
        return questionRepository.findAllByUserId(uid);
    }
}
