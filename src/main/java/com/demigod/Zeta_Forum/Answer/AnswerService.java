package com.demigod.Zeta_Forum.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public void addAns(Long qid,Long uid,Answer ans) {
        ans.setQid(qid);
        ans.setUserId(uid);
        answerRepository.save(ans);
    }

    public List<Answer> getAllAnswerForQuestion(Long qid) {

        return answerRepository.findAllByQid(qid);
    }
}
