package com.demigod.Zeta_Forum.Question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Question {
    @Id
    private String questionId;

    @NotNull
    private String question;

    @NotNull
    private String userId;

    private long createdOn;

    private long updatedOn;

    public Question()
    {

    }

    public Question(String questionId, String question,Date createdOn,Date updatedOn,String userId)
    {
        this.userId=userId;
        this.question=question;
        this.updatedOn=updatedOn.getTime()/1000;
        this.createdOn=createdOn.getTime()/1000;
        this.questionId=questionId;
    }

    public String getQid() {
        return questionId;
    }

    public void setQid(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn.getTime()/1000;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn.getTime()/1000;
    }
}
