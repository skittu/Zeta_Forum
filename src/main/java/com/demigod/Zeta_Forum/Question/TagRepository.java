package com.demigod.Zeta_Forum.Question;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TagRepository extends CrudRepository<Tag,TagId> {

    @Modifying
    @Transactional
    long deleteByQuestionId(String questionId);

    List<Tag> findByTagNameIn(List<String> tags);



    void deleteByTagId(TagId tagId);
}
