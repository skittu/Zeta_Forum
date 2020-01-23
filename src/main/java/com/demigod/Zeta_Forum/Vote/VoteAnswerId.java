package com.demigod.Zeta_Forum.Vote;

import com.demigod.Zeta_Forum.Answer.Answer;
import com.demigod.Zeta_Forum.Question.TagId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VoteAnswerId implements Serializable {



    @NotNull
    private String userId;

    @NotNull
    private String answerId;

    public VoteAnswerId()
    {

    }

    public VoteAnswerId(String userId,String answerId)
    {
        this.answerId=answerId;
        this.userId=userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteAnswerId that = (VoteAnswerId) o;

        if (answerId.equals(that.answerId)) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + answerId.hashCode();
        return result;
    }
}
