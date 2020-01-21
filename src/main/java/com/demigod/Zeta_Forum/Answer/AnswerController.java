package com.demigod.Zeta_Forum.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method = RequestMethod.POST,value = "/answer")
    public Answer addAnswer(@RequestBody Answer answer, @RequestParam String questionId,@RequestParam String userId)
    {
       return  answerService.addAnswer(questionId,userId,answer);
    }

    @RequestMapping(value = "/answer")
    public List<Answer> getAllAnswerForQuestion(@RequestParam String questionId ,
                                                @RequestParam(defaultValue = "0")Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "createdOn") String sortBy)
    {
        return answerService.getAllAnswerForQuestion(questionId,pageNo,pageSize,sortBy);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/answer/{answerId}")
    public Answer updateAnswer(@RequestBody Answer answer,@PathVariable String answerId)
    {
        return  answerService.updateAnswer(answer,answerId);
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/answer/{answerId}")
    public String deleteAnswer(@PathVariable String answerId)
    {
        return  answerService.deleteAnswer(answerId);
    }



}
