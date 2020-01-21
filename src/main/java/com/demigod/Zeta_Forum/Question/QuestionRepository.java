package com.demigod.Zeta_Forum.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,String>, PagingAndSortingRepository<Question,String> {


   List<Question> findAllByUserId(String id);



    @Modifying
    @Transactional
    void deleteByQuestionId(String questionId);

}
