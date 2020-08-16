package com.sqs.demo.model;

import com.sqs.demo.constants.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SNSNotification {

    private NotificationType notificationType;
    private Map<String, String> messageAttributes;
}
