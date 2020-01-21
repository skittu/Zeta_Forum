package com.demigod.Zeta_Forum.Question;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {

    List<Question> findAllByUserId(Long id);


}
