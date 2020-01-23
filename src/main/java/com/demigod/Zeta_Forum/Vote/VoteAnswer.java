package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Question.TagId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(VoteAnswerId.class)
public class VoteAnswer {

    @Id
    @NotNull
    private String userId;

    @Id
    @NotNull
    private String answerId;

    @NotNull
    private long vote;

    public VoteAnswer()
    {

    }
    public VoteAnswer(String userId,String answerId,long vote)
    {
        this.answerId=answerId;
        this.userId=userId;
        this.vote=vote;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getVote() {
        return vote;
    }

    public void setVote(long vote) {
        this.vote = vote;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
