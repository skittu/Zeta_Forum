package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Question.TagId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VoteQuestionId implements Serializable {


    @NotNull
    private String userId;

    @NotNull
    private String questionId;

    public VoteQuestionId()
    {

    }
    public VoteQuestionId(String userId,String questionId)
    {
        this.questionId=questionId;
        this.userId=userId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteQuestionId that = (VoteQuestionId) o;

        if (questionId.equals(that.questionId)) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + questionId.hashCode();
        return result;
    }
}
