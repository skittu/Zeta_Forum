package com.demigod.Zeta_Forum.Answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,String> {


    Page<Answer> findAllByQuestionId(String qid, Pageable pageable);
}
