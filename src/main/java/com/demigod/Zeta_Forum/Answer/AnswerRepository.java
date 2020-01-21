package com.demigod.Zeta_Forum.Answer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,Long> {


    List<Answer> findAllByQid(Long qid);
}
