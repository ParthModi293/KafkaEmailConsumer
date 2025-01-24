
package com.email.kafkaemailconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import org.common.common.ResponseBean;
import org.communication.dto.EmailDto;
import org.communication.service.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import org.springframework.stereotype.Service;


@Service
@Log4j2
public class EmailUtil {

    private final ObjectMapper objectMapper;

    private final EmailSenderService emailSenderService;


    public EmailUtil(ObjectMapper objectMapper, EmailSenderService emailSenderService) {

        this.objectMapper = objectMapper;
        this.emailSenderService = emailSenderService;
    }

    public ResponseBean<Void> sendEmailRestCall(EmailDto emailDto) {
        emailSenderService.sendEmail(emailDto);
        return new ResponseBean<>(HttpStatus.OK,"Email sent successfully","Email sent successfully",null);
    }



    @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"1"}), groupId = "email-group", errorHandler = "myErrorHandler")
    public void emailConsumer1P1(String message) {
        try {
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

            emailSenderService.sendEmail(emailDto);
        } catch (Exception e) {
            log.error(e);
        }
    }

    /* TODO : Remaining for kafka
     @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"1"}), groupId = "email-group", errorHandler = "myErrorHandler")
   public void emailConsumer2P1(String message) {
       try {
           EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);
           sendEmail(emailDto);
           System.out.println("2 *********************************************************** 2");
       } catch (Exception e) {
           log.error(e);
       }
   }*/
    @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"2"}), groupId = "email-group", errorHandler = "myErrorHandler")
    public void emailConsumer2(String message) {
        try {
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

            emailSenderService.sendEmail(emailDto);

        } catch (Exception e) {
            log.error(e);

        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"3"}), groupId = "email-group", errorHandler = "myErrorHandler")
    public void emailConsumer3(String message) {
        try {
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

            emailSenderService.sendEmail(emailDto);

        } catch (Exception e) {
            log.error(e);

        }
    }


}
