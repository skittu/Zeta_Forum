package com.demigod.Zeta_Forum.Question;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface TagRepository extends CrudRepository<Tag,TagId> {

    @Modifying
    @Transactional
    long deleteByQuestionId(String questionId);

//    public void deleteByQuestionId(String questionId);
//
//    void deleteByTagId(TagId tagId);
}
