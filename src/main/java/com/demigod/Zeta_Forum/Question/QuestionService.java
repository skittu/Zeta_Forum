package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;
  
   @Autowired
    private TagRepository tagRepository;

   // Question functions
  
    public Question addQuestion(QuestionPostedFrontend que,String userId) {
 

        // storing question in repository
        UUID questionUUID = UUID.randomUUID();
        Date date = new Date();
        Question questionToBeInserted=new Question(
                questionUUID.toString(),
                que.getQuestion().toLowerCase(),
                date,
                date,
                userId);
        questionRepository.save(questionToBeInserted);
        // storing tags in repository
        addTags(que,questionUUID.toString());

        return questionToBeInserted;
    }


    public Page<Question> getAllQuestions(List<String> tags,String userId,String sortBy,Integer pageSize,Integer pageNumber)
    {
        System.out.println(pageSize);
        Pageable page = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());

        if(tags.isEmpty())
        {

            if( userId.equals("") )
            {

                return questionRepository.findAll(page);
            }
            else
            {

                return questionRepository.findAllByUserId(userId, page);
            }
        }
        else
        {
            List<Tag> questionsToBeFetched= tagRepository.findByTagNameIn(tags);
            List<String> listOfQuestionId= questionsToBeFetched.stream().map( t -> t.getQuestionId())
                    .collect(Collectors.toList());
            System.out.println(listOfQuestionId.get(0));

            if(userId.equals(""))
            {
                return questionRepository.findByQuestionIdIn(listOfQuestionId,page);
            }
            else
            {
                return questionRepository.findByQuestionIdInAndUserId(listOfQuestionId,userId,page);
            }
        }

    }


    public Question updateQuestion(QuestionPostedFrontend questionBody, String userId, String questionId) {

        // updating question
        Date date = new Date();
        Question questionToBeUpdated =  questionRepository.findById(questionId).get();
        questionToBeUpdated.setQuestion(questionBody.getQuestion());
        questionToBeUpdated.setUpdatedOn(date);
        questionRepository.save(questionToBeUpdated);


        //deleting tags
        deleteTags(questionId);


        // adding tags
        addTags(questionBody,questionId);

        return questionRepository.findById(questionId).get();
    }


    public Question deleteQuestion(String questionId, String userId) {

        // deleteing questions
            questionRepository.deleteByQuestionId(questionId);

        // deleting tags
            deleteTags(questionId);

            return questionRepository.findById(questionId).get();
    }

    // Tags functions

    public void addTags(QuestionPostedFrontend que, String questionUUID)
    {
        List<Tag> tagList = que.getQuestionTags().stream()
                .map(t -> new Tag(t , questionUUID.toString())).collect(Collectors.toList());
        tagRepository.saveAll(tagList);
    }

    public void deleteTags(String questionId)
    {
        long recordsDeleted = tagRepository.deleteByQuestionId(questionId);
    }


}
