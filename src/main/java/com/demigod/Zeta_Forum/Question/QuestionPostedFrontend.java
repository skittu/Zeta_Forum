package com.demigod.Zeta_Forum.Question;

import java.util.List;

public class QuestionPostedFrontend {



    private String question;

    private List<String> questionTags;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getQuestionTags() {

        return questionTags;
    }

    public void setQuestionTags(List<String> questionTags) {

        this.questionTags = questionTags;
    }
}
