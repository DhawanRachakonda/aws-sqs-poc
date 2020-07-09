package com.sqs.demo.web;

import com.sqs.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    @PostMapping("/sqs")
    public ResponseEntity<?> savePerson(@RequestBody Person person){
        this.queueMessagingTemplate.convertAndSend("imap-queue", person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }
}
