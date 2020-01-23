package com.demigod.Zeta_Forum.Vote;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(VoteQuestionId.class)
public class VoteQuestion {

    @NotNull
    @Id
    private String userId;

    @NotNull
    @Id
    private String questionId;

    @NotNull
    private long vote;

    public VoteQuestion() {

    }

    public VoteQuestion(String userId, String answerId,long vote)
    {
        this.questionId=answerId;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
