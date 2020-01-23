package com.demigod.Zeta_Forum.Question;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ReturnQuestion{

    private Long totalPages;

    private Long totalElements;

    private Long numberOfElements;

    private List<Question> questions=null;

    private List<List<String> >  tags;


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<List<String>> getTags() {
        return tags;
    }

    public void setTags(List<List<String>> tags) {
        this.tags = tags;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
