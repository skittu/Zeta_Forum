package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Answer.Answer;
import com.demigod.Zeta_Forum.Question.Question;
import com.demigod.Zeta_Forum.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = {"*"})
@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/voteQuestion")
    public Question questionVote(@RequestParam String userId, @RequestParam String questionId, @RequestParam Integer vote)
    {

            return voteService.voteQuestion(userId,questionId,vote);
    }

    @PostMapping(value = "/voteAnswer")
    public Answer answerVote(@RequestParam String userId, @RequestParam String answerId, @RequestParam Integer vote)
    {

        return voteService.voteAnswer(userId,answerId,vote);
    }

}
