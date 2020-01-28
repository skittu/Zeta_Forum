package com.demigod.Zeta_Forum.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={"*"})
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
    public SendFront getAllAnswerForQuestion(@RequestParam String questionId ,
                                                @RequestParam(defaultValue = "0")Integer pageNumber,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "upVote") String sortBy)
    {
        return answerService.getAllAnswerForQuestion(questionId,pageNumber,pageSize,sortBy);
    }


    @RequestMapping(value = "/getsingleanswer")
    public Optional<Answer> getsingleanswer(@RequestParam String answerId)
    {
        return answerService.getSingleAnswer(answerId);
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
