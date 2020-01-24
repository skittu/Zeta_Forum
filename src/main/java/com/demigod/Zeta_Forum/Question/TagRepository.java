package com.demigod.Zeta_Forum.Question;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TagRepository extends CrudRepository<Tag,TagId> {

    @Modifying
    @Transactional
    long deleteByQuestionId(String questionId);


    List<Tag> findAllByTagName(String tag);


    List<Tag> findAllByQuestionId(String questionId);

    @Query(nativeQuery = true,value = "Select question_id from tag where tag_name in ?1 group by question_id having count(question_id) = ?2")
    List<String> findQuestionId(List<String> tags,Integer count);

//    void deleteByTagId(TagId tagId);
}
