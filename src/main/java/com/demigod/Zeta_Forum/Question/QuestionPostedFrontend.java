package com.demigod.Zeta_Forum.Question;

import javax.persistence.Lob;
import java.util.List;

public class QuestionPostedFrontend{

    @Lob
    private String question;

    private List<String> questionTags;

    public List<String> getQuestionTags() {

        return questionTags;
    }

    public void setQuestionTags(List<String> questionTags) {

        this.questionTags = questionTags;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
