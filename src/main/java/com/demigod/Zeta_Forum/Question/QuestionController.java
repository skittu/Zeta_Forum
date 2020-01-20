package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.POST,value = "/question")
    public void addQuestion(@RequestBody Question que)
    {
        questionService.addQue(que);
    }

    @RequestMapping(value = "/question")
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }




}
