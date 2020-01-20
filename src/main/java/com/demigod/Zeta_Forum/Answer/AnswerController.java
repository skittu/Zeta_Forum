package com.demigod.Zeta_Forum.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method = RequestMethod.POST,value = "/answer/{qid}/{uid}")
    public void addQuestion(@RequestBody Answer ans, @PathVariable Long qid,@PathVariable Long uid)
    {
        answerService.addAns(qid,uid,ans);
    }

}
