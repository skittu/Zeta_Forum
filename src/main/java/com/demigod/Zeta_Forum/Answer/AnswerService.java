package com.demigod.Zeta_Forum.Answer;
import javassist.bytecode.analysis.Analyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Transactional
@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer addAnswer(String questionId,String userId,Answer ans) {

        UUID answerUUID = UUID.randomUUID();
        Date date = new Date();


        ans.setQuestionId(questionId);
        ans.setUserId(userId);
        ans.setAnswerId(answerUUID.toString());
        ans.setCreatedOn(date);
        ans.setUpdatedOn(date);
        answerRepository.save(ans);
        return ans;
    }

    public SendFront getAllAnswerForQuestion(String questionId,Integer pageNumber,Integer pageSize,String sortBy) {

        Pageable ab = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        Page<Answer> pagedResult=answerRepository.findAllByQuestionId(questionId,ab);

        SendFront sTemp=new SendFront();

        sTemp.setPageNo(pagedResult.getNumber());
        sTemp.setTotalItems(pagedResult.getTotalElements());
        sTemp.setPageSize(pagedResult.getSize());
        sTemp.setAnswer(pagedResult.getContent());


        return sTemp;
//        if(pagedResult.hasContent()) {
//          System.out.println();
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Answer>();
//        }
    }

    public Answer updateAnswer(Answer answer,String answerId) {

        Date date = new Date();
        Answer temp=answerRepository.findById(answerId).get();
        temp.setAnswer(answer.getAnswer());
        temp.setUpdatedOn(date);
        answerRepository.save(temp);
        return temp;

    }

    public String deleteAnswer(String answerId) {
        answerRepository.deleteById(answerId);
        return "done";
    }
}
