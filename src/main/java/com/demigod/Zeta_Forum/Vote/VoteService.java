package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Answer.Answer;
import com.demigod.Zeta_Forum.Answer.AnswerRepository;
import com.demigod.Zeta_Forum.Question.Question;
import com.demigod.Zeta_Forum.Question.QuestionRepository;
import com.demigod.Zeta_Forum.Question.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {


    @Autowired
    private VoteAnswerRepository voteAnswerRepository;

    @Autowired
    private VoteQuestionRepository voteQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public void answerService(String userId, String answerId, long vote)
    {

       if(voteAnswerRepository.findById(new VoteAnswerId(userId,answerId)).isEmpty())
       {
           voteAnswerRepository.save(new VoteAnswer(userId,answerId,vote));
           if(vote==1)
           {

           }
           else
           {

           }
       }
       else
       {
           VoteAnswer voteAnswer = voteAnswerRepository.findById(new VoteAnswerId(userId,answerId)).get();
           voteAnswer.setVote(vote);
           voteAnswerRepository.save(voteAnswer);

           if(vote==1)
           {

           }
           else
           {

           }
       }

    }


    public void questionService(String userId, String questionId, long vote)
    {
        Optional<VoteQuestion>  vQ= voteQuestionRepository.findById(new VoteQuestionId(userId,questionId));
        Question q = questionRepository.findById(questionId).get();

        if(vQ.isEmpty() || vQ.get().getVote()==(long)0)
        {
            voteQuestionRepository.save(new VoteQuestion(userId,questionId,vote));

            if(vote==(long)1)
            {
                q.setUpvotes(q.getUpvotes()+1);
            }
            else
            {
                q.setDownvotes(q.getDownvotes()+1);
            }
            questionRepository.save(q);

        }
        else
        {


            if(vote==(long)1 && vQ.get().getVote()==(long)1)
            {
                voteQuestionRepository.save(new VoteQuestion(userId,questionId,0));

            }
            else if(vote==(long)2 && vQ.get().getVote()==(long)1)
            {

            }
            else if(vote==(long)1 && vQ.get().getVote()==(long)2)
            {

            }
            else
            {
                voteQuestionRepository.save(new VoteQuestion(userId,questionId,0));
            }
        }
    }

}
