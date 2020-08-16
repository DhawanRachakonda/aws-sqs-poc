package com.sqs.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.demo.model.SNSNotification;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class SNSConfig {

    AWSCredentials credentials = new BasicAWSCredentials(
            "",
            ""
    );

    @Bean
    public AmazonSNSAsync buildAmazonSNSClientAsync() {
        return AmazonSNSAsyncClientBuilder
                .standard()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNSAsync amazonSNSAsync,
                                                                       ResourceIdResolver resourceIdResolver) {
        return new NotificationMessagingTemplate(amazonSNSAsync, resourceIdResolver);
    }
}
