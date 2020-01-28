package com.demigod.Zeta_Forum.Question;


import com.demigod.Zeta_Forum.Answer.Answer;

import com.demigod.Zeta_Forum.User.UserRepository;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Transactional
@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    // Question functions


    public Question addQuestion(QuestionPostedFrontend que, String userId) {


        // storing question in repository
        UUID questionUUID = UUID.randomUUID();
        Date date = new Date();
        Question questionToBeInserted = new Question(
                questionUUID.toString(),
                que.getQuestion().toLowerCase(),
                date, date,
                userId);
        questionRepository.save(questionToBeInserted);

        // storing tags in repository
        addTags(que, questionUUID.toString());

        return questionToBeInserted;
    }


    public ReturnQuestion getAllQuestions(String searchBy, String searchString, List<String> tags, String userId, String sortBy, Integer searchOrder, Integer pageSize, Integer pageNumber) throws IOException {

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        System.out.println(searchBy);
        if (searchBy.equals("Text")) {
            System.out.println(searchString);
            String s = "";
            try {
                Pattern p = Pattern.compile("\\b(he|she|the|...)\\b\\s?");
                Matcher m = p.matcher(searchString);
                s = m.replaceAll(" ");


                s.replaceAll("[^a-zA-Z0-9 ]", "");
                String[] temp = s.split(" ");
                String finalString = "";
                for (int i = 0; i < temp.length - 1; i++) {
                    finalString += temp[i];
                    finalString += " | ";
                }
                finalString += temp[temp.length - 1];

                System.out.println(finalString);
                List<String> l = questionRepository.SearchQuestion(finalString);

                return buildList(questionRepository.findByQuestionIdIn(l, page));
            } catch (Exception e) {
                return new ReturnQuestion();
            }
        } else if (searchBy.equals("Tag")) {

            List<String> listOfQuestionId = tagRepository.findQuestionId(tags, tags.size());

            return buildList(questionRepository.findByQuestionIdIn(listOfQuestionId, page));

        } else if (searchBy.equals("Author")) {

            return buildList(questionRepository.findAllByUserId(userId, page));

        } else {

            return buildList(questionRepository.findAll(page));

        }


    }


    public Question updateQuestion(QuestionPostedFrontend questionBody, String userId, String questionId) {

        // updating question
        Date date = new Date();
        Question questionToBeUpdated = questionRepository.findById(questionId).get();

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
        } catch (Exception e) {
            return "Some error occurred";
        }

    }

    // Tags functions

    public void addTags(QuestionPostedFrontend que, String questionUUID) {
        List<Tag> tagList = que.getQuestionTags().stream()
                .map(t -> new Tag(t, questionUUID.toString())).collect(Collectors.toList());
        tagRepository.saveAll(tagList);
    }

    public void deleteTags(String questionId) {
        long recordsDeleted = tagRepository.deleteByQuestionId(questionId);
    }

    public List<String> getTags(String questionId) {

        return tagRepository.findAllByQuestionId(questionId).stream().map(t -> t.getTagName()).collect(Collectors.toList());

    }


    public ReturnQuestion buildList(Page<Question> page) {

        ReturnQuestion returnQuestion = new ReturnQuestion();
        returnQuestion.setNumberOfElements(page.getNumberOfElements());
        returnQuestion.setTotalElements(page.getTotalElements());
        returnQuestion.setTotalPages(page.getTotalPages());
        returnQuestion.setQuestions(page.getContent());
        List<List<String>> tags = new ArrayList<>();
        List<String> userName = new ArrayList<>();

        for (int i = 0; i < page.getContent().size(); i++) {
            tags.add(getTags(page.getContent().get(i).getQuestionId()));
           userName.add(userRepository.findById(page.getContent().get(i).getUserId()).get().getUserName());
        }
        returnQuestion.setUserName(userName);
        returnQuestion.setTags(tags);


        return returnQuestion;
    }


    public Optional<Question> getSingleQuestion(String questionId) {
        return questionRepository.findById(questionId);
    }

    public List<String> loadstop() throws IOException {
        List<String> stopwrods = Files.readAllLines(Paths.get("english_stopwords.txt"));
        for (int i = 0; i < 10; i++)
            System.out.println(stopwrods.get(i));
        return stopwrods;

    }
}
