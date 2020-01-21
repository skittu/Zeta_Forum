package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Question updateQuestion(@RequestBody QuestionPostedFrontend questionBody,@RequestParam String userId,
                                 @PathVariable("questionId") String questionId)
    {
        return questionService.updateQuestion(questionBody,userId,questionId);

    }


    @DeleteMapping(value = "/question/{questionId}")
    public Question deleteQuestion(@PathVariable("questionId") String questionId,@RequestParam String userId)
    {
        return questionService.deleteQuestion(questionId,userId);

    }

    @PostMapping(value = "/questions")
    public Page<Question> getQuestions(@RequestBody QuestionPostedFrontend tags, @RequestParam(defaultValue = "") String userId,
                                       @RequestParam(defaultValue = "createdOn") String sortBy,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "0") Integer pageNumber)
    {

        return questionService.getAllQuestions(tags.getQuestionTags(),userId,sortBy,pageSize,pageNumber);

    }

//    @RequestMapping(value = "/question/{uid}")
//    public List<Question> getAllUserQuestions(@PathVariable String userId)
//    {
//        return questionService.getAllQuestionsOfThisUser(userId);
//    }
//
//    @RequestMapping(value = "/question")
//    public List<Question> getAllQuestions()
//    {
//        return questionService.getAllQuestions();
//    }




}
