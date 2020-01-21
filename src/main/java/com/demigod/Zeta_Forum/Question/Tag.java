package com.demigod.Zeta_Forum.Question;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(TagId.class)
public class Tag {

    @Id
    private String tagName;
    @Id
    private String questionId;

    public Tag()
    {

    }
    public Tag(String tagName,String questionId)
    {
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
}
