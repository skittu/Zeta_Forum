package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Answer.Answer;
import com.demigod.Zeta_Forum.Answer.AnswerRepository;
import com.demigod.Zeta_Forum.Question.Question;
import com.demigod.Zeta_Forum.Question.QuestionRepository;
import com.demigod.Zeta_Forum.Question.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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

    public Answer voteAnswer (String userId, String answerId, Integer vote)
    {

        System.out.println("Vote Answer Service got it");

        VoteAnswer vA = null;
        try {
            vA=voteAnswerRepository.findById(new VoteAnswerId(userId, answerId)).get();
        }
        catch (Exception e)
        {
            vA=null;
        }


        Answer a = answerRepository.findById(answerId).get();

        if(vA==null || vA.getVote()==0)
        {
            voteAnswerRepository.save(new VoteAnswer(userId,answerId,vote));

            if(vote==1)
            {
                a.setUpVote(a.getUpVote()+1);
            }
            else
            {
                a.setDownVote(a.getDownVote()+1);
            }
            answerRepository.save(a);

        }
        else
        {


            if(vote==1 && vA.getVote()==1)
            {
                voteAnswerRepository.save(new VoteAnswer(userId,answerId,0));
                a.setUpVote(a.getUpVote()-1);

            }
            else if(vote==2 && vA.getVote()==1)
            {
                voteAnswerRepository.save(new VoteAnswer(userId,answerId,2));
                a.setUpVote(a.getUpVote()-1);
                a.setDownVote(a.getDownVote()+1);
            }
            else if(vote==1 && vA.getVote()==2)
            {
                voteAnswerRepository.save(new VoteAnswer(userId,answerId,1));
                a.setUpVote(a.getUpVote()+1);
                a.setDownVote(a.getDownVote()-1);

            }
            else
            {
                voteAnswerRepository.save(new VoteAnswer(userId,answerId,0));
                a.setDownVote(a.getDownVote()-1);

            }

            answerRepository.save(a);
        }
        return answerRepository.findById(answerId).get();

    }


    public Question voteQuestion(String userId, String questionId, Integer vote)
    {
        System.out.println("Vote Question Service got it");
        VoteQuestion vQ = null;
        try {
            vQ=voteQuestionRepository.findById(new VoteQuestionId(userId, questionId)).get();
        }
        catch (Exception e)
        {
            vQ=null;
        }


        Question q = questionRepository.findById(questionId).get();

        if(vQ==null || vQ.getVote()==0)
        {
            voteQuestionRepository.save(new VoteQuestion(userId,questionId,vote));

            if(vote==1)
            {
                q.setUpVote(q.getUpVote()+1);
            }
            else
            {
                q.setDownVote(q.getDownVote()+1);
            }
            questionRepository.save(q);

        }
        else
        {


            if(vote==1 && vQ.getVote()==1)
            {
                voteQuestionRepository.save(new VoteQuestion(userId,questionId,0));
                q.setUpVote(q.getUpVote()-1);

            }
            else if(vote==2 && vQ.getVote()==1)
            {
                voteQuestionRepository.save(new VoteQuestion(userId,questionId,2));
                q.setUpVote(q.getUpVote()-1);
                q.setDownVote(q.getDownVote()+1);
            }
            else if(vote==1 && vQ.getVote()==2)
            {
                voteQuestionRepository.save(new VoteQuestion(userId,questionId,1));
                q.setUpVote(q.getUpVote()+1);
                q.setDownVote(q.getDownVote()-1);

            }
            else
            {
                voteQuestionRepository.save(new VoteQuestion(userId,questionId,0));
                q.setDownVote(q.getDownVote()-1);

            }

            questionRepository.save(q);
        }
        return questionRepository.findById(questionId).get();
    }


    // Not tested yet

    public VoteQuestion getQuestionVote(String questionId,String userId)
    {
        return voteQuestionRepository.findById(new VoteQuestionId(userId,questionId)).get();
    }
    public VoteAnswer getAnswerVote(String answerId,String userId)
    {
        return voteAnswerRepository.findById(new VoteAnswerId(userId,answerId)).get();
    }

}
