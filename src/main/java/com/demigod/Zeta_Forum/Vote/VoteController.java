package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/voteQuestion")
    public void questionVote(@RequestParam String userId,@RequestParam String questionId,@RequestParam Integer vote)
    {
        System.out.println("adsda");
            voteService.questionService(userId,questionId,vote);
    }

    @PostMapping(value = "/voteAnswer")
    public void answerVote(@RequestParam String userId,@RequestParam String answerId,@RequestParam Integer vote)
    {
        System.out.println("adsda");
        voteService.answerService(userId,answerId,vote);
    }

}
