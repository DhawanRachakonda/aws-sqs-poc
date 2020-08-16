package com.sqs.demo.listeners;

import com.sqs.demo.model.Person;
import com.sqs.demo.model.SNSNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import javax.management.Notification;

@Slf4j
@Service
public class SqsListenerService {

    @SqsListener("imap-queue")
    public void listenToMessage(Person person, @Header("MessageId") String messageId,
                                @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
        log.info("Received Message {} with messageId {} at {}", person, messageId, approximateFirstReceiveTimestamp);
    }

    @SqsListener(value = "agent_service", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listenToSNSMessage(@NotificationMessage SNSNotification notification, @Header("MessageId") String messageId,
                                   @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp,
                                   Acknowledgment ack) {
        log.info("Received Message {} with messageId {} at {}", notification, messageId, approximateFirstReceiveTimestamp);
        ack.acknowledge();
    }


}
