package com.demigod.Zeta_Forum.Question;

import java.io.Serializable;
import java.security.SecureRandom;

public class TagId implements Serializable {

    private String tagName;
    private String questionId;

    public TagId(){}

    public TagId(String tagName,String questionId){
        this.questionId=questionId;
        this.tagName=tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagId that = (TagId) o;

        if (!tagName.equals(that.tagName)) return false;
        return questionId.equals(that.questionId);
    }
    @Override
    public int hashCode() {
        int result = tagName.hashCode();
        result = 31 * result + questionId.hashCode();
        return result;
    }
}
