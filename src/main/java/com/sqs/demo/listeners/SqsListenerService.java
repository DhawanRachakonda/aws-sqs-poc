package com.sqs.demo.listeners;

import com.sqs.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqsListenerService {

    @SqsListener("imap-queue")
    public void listenToMessage(Person person, @Header("MessageId") String messageId,
                                @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
        log.info("Received Message {} with messageId {} at {}", person, messageId, approximateFirstReceiveTimestamp);
    }
}
