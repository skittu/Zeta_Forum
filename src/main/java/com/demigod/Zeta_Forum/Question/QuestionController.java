package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/question")
    public String addQuestion(@RequestBody QuestionPostedFrontend questionBody,@RequestParam String userId)
    {

        questionService.addQuestion(questionBody, userId);

        return "done";


    }

    @RequestMapping(value = "/question/{uid}")
    public List<Question> getAllUserQuestions(@PathVariable String userId)
    {
        return questionService.getAllQuestionsOfThisUser(userId);
    }

    @RequestMapping(value = "/question")
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }




}
