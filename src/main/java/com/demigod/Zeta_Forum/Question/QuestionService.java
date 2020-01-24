package com.demigod.Zeta_Forum.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Transactional
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


    public ReturnQuestion getAllQuestions(String searchBy,String searchString,List<String> tags,String userId,String sortBy,Integer searchOrder,Integer pageSize,Integer pageNumber)
    {

        Pageable page = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
        System.out.println(searchBy);
        if(searchBy.equals("Text"))
        {

            // Need to be changed for search string
            return buildList(questionRepository.findAll(page));

        }
        else if(searchBy.equals("Tag"))
        {
            System.out.println(tags.size());
            List<Tag> questionsToBeFetched= tagRepository.findAllByTagName(tags.get(0));
            List<String> listOfQuestionId= questionsToBeFetched.stream().map( t -> t.getQuestionId())
                    .collect(Collectors.toList());

            for(int i=1;i<tags.size();i++)
            {
                List<Tag> tempFetch= tagRepository.findAllByTagName(tags.get(i));
                List<String> tempId= tempFetch.stream().map( t -> t.getQuestionId())
                        .collect(Collectors.toList());
                Set<String> result = tempId.stream()
                        .distinct()
                        .filter(listOfQuestionId::contains)
                        .collect(Collectors.toSet());
                listOfQuestionId= convertSetToList(result);
            }

            if(userId.equals(""))
            {
                return  buildList(questionRepository.findByQuestionIdIn(listOfQuestionId,page));

            }
            else
            {
                return buildList(questionRepository.findByQuestionIdInAndUserId(listOfQuestionId,userId,page));
            }
        }
        else
        {
            if( userId.equals("") )
            {

                return buildList(questionRepository.findAll(page));

            }
            else
            {

                return buildList(questionRepository.findAllByUserId(userId, page));
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
            addTags(questionBody, questionId);

            return questionRepository.findById(questionId).get();

    }


    public String deleteQuestion(String questionId, String userId) {

        try {

        // deleteing questions
        questionRepository.deleteByQuestionId(questionId);

        // deleting tags
        deleteTags(questionId);

        return "Successfully deleted";
        }
        catch(Exception e)
        {
            return "Some error occurred";
        }

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

    public List<String> getTags(String questionId)
    {

        return tagRepository.findAllByQuestionId(questionId).stream().map(t -> t.getTagName()).collect(Collectors.toList());

    }


    public static <T> List<T> convertSetToList(Set<T> set)
    {
        // create an empty list
        List<T> list = new ArrayList<>();

        // push each element in the set into the list
        for (T t : set)
            list.add(t);

        // return the list
        return list;
    }

    public ReturnQuestion buildList(Page<Question> page)
    {

        ReturnQuestion returnQuestion = new ReturnQuestion();
        returnQuestion.setNumberOfElements(page.getNumberOfElements());
        returnQuestion.setTotalElements(page.getTotalElements());
        returnQuestion.setTotalPages(page.getTotalPages());
        returnQuestion.setQuestions(page.getContent());
        List<List<String> > tags = new ArrayList<>();

        for(int i=0;i<page.getContent().size();i++)
        {
            tags.add(getTags(page.getContent().get(i).getQuestionId()));
        }
        returnQuestion.setTags(tags);



        return returnQuestion;
    }

}
