package com.sqs.demo.web;

import com.sqs.demo.model.Person;
import com.sqs.demo.model.SNSNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    @PostMapping("/sns")
    public ResponseEntity<?> savePerson(@RequestBody SNSNotification notification){
        this.notificationMessagingTemplate.convertAndSend("user_registration", notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
