package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.POST,value = "/question/{uid}")
    public void addQuestion(@RequestBody Question que,@PathVariable Long uid)
    {
        questionService.addQue(uid,que);
    }

    @RequestMapping(value = "/question/{uid}")
    public List<Question> getAllUserQuestions(@PathVariable Long uid)
    {
        return questionService.getAllQuestionsOfThisUser(uid);
    }

    @RequestMapping(value = "/question")
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }




}
