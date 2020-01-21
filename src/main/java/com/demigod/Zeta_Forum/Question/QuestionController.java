package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/question")
    public Question addQuestion(@RequestBody QuestionPostedFrontend questionBody,@RequestParam String userId)
    {

        return questionService.addQuestion(questionBody, userId);

    }

    @PutMapping(value = "/question/{questionId}")
    public String updateQuestion(@RequestBody QuestionPostedFrontend questionBody,@RequestParam String userId,
                                 @PathVariable("questionId") String questionId)
    {
        questionService.updateQuestion(questionBody,userId,questionId);
        return "Done";
    }


    @DeleteMapping(value = "/question/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") String questionId,@RequestParam String userId)
    {
        questionService.deleteQuestion(questionId,userId);
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
