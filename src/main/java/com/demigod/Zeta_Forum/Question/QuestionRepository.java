package com.demigod.Zeta_Forum.Question;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,String>, PagingAndSortingRepository<Question,String> {


    @Modifying
    @Transactional
    void deleteByQuestionId(String questionId);


   Page<Question> findAllByUserId(String id,Pageable pageable);

    Page<Question> findByQuestionIdIn(List<String> listOfQuestionId,Pageable pageable);

    Page<Question> findByQuestionIdInAndUserId(List<String> listOfQuestionId,String userId,Pageable page);

    @Query(nativeQuery = true,value = "Select question_id from question where to_tsvector(question) @@ to_tsquery(?1)")
    List<String> SearchQuestion(String s);
}
