package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void addQuestion(QuestionPostedFrontend que,String userId) {

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


    }

    public List<Question> getAllQuestions() {
        List<Question> ques=new ArrayList<>();
        questionRepository.findAll()
                .forEach(ques::add);
        return ques;
    }

    public List<Question> getAllQuestionsOfThisUser(String userId) {
        List<Question> ques=new ArrayList<>();
        return questionRepository.findAllByUserId(userId);
    }

    public void updateQuestion(QuestionPostedFrontend questionBody, String userId, String questionId) {

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


    }


    public void deleteQuestion(String questionId, String userId) {

        // deleteing questions
            questionRepository.deleteByQuestionId(questionId);

        // deleting tags
            deleteTags(questionId);

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
