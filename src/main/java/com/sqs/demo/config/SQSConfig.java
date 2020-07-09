package com.sqs.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

import java.util.Collections;

@Configuration
@EnableSqs
public class SQSConfig {

    AWSCredentials credentials = new BasicAWSCredentials(
            "",
            ""
    );

    @Bean("awsSqsAsync")
    @Primary
    public AmazonSQSAsync getAmazonSqsAsync() {
        AmazonSQSAsync amazonSqsAsync = AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_1)
                .build();
        return amazonSqsAsync;
    }

//    @Bean
//    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
//        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//
//        //set strict content type match to false
//        messageConverter.setStrictContentTypeMatch(false);
//        factory.setArgumentResolvers(Collections.<HandlerMethodArgumentResolver>singletonList(new PayloadArgumentResolver(messageConverter)));
//        return factory;
//    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(getAmazonSqsAsync());
        factory.setMaxNumberOfMessages(10);
        return factory;
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(
            AmazonSQSAsync amazonSQSAsync,
            ResourceIdResolver resourceIdResolver) {
        return new QueueMessagingTemplate(amazonSQSAsync, resourceIdResolver);
    }
}
