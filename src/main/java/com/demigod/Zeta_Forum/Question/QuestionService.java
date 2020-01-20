package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;

    public void addQue(Question que)
    {
        questionRepository.save(que);
    }

    public List<Question> getAllQuestions() {
        List<Question> ques=new ArrayList<>();
        questionRepository.findAll()
                .forEach(ques::add);
        return ques;
    }
}
