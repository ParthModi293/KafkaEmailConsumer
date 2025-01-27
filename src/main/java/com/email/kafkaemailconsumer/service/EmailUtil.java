package com.email.kafkaemailconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;


import org.common.common.Const;
import org.communication.config.MessageService;
import org.communication.dto.EmailDto;
import org.communication.service.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import org.springframework.stereotype.Service;
import org.common.common.ResponseBean;



@Service
@Log4j2
public class EmailUtil {

    private final ObjectMapper objectMapper;

    private final EmailSenderService emailSenderService;

    private final MessageService messageService;




    public EmailUtil(ObjectMapper objectMapper, EmailSenderService emailSenderService, MessageService messageService) {

        this.objectMapper = objectMapper;
        this.emailSenderService = emailSenderService;

        this.messageService = messageService;
    }

    public ResponseBean<Void> sendEmailRestCall(EmailDto emailDto) {
        try {
            emailSenderService.sendEmail(emailDto);
            return new ResponseBean<>(HttpStatus.OK, Const.rCode.SUCCESS,messageService.getMessage("EMAIL_SUCCESS") , messageService.getMessage("EMAIL_SUCCESS"), null);
        } catch (Exception e) {
            log.error(e);
            return new ResponseBean<>(HttpStatus.OK,Const.rCode.BAD_REQUEST, messageService.getMessage("EMAIL_FAILURE"), messageService.getMessage("EMAIL_FAILURE"), null);
        }


    }

// TODO:  pariorty 1 directly call the mail sender service
   /* @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"1"}), groupId = "email-group", errorHandler = "myErrorHandler")
    public void emailConsumer1P1(String message) {
        try {
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

            emailSenderService.sendEmail(emailDto);
        } catch (Exception e) {
            log.error(e);
        }
    }*/

    @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"2"}), groupId = "email-group"/*, errorHandler = "myErrorHandler"*/)
    public void emailConsumer2(String message) {
        try {
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

            emailSenderService.sendEmail(emailDto);

        } catch (Exception e) {
            log.error(e);

        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "email-topic", partitions = {"3"}), groupId = "email-group"/*, errorHandler = "myErrorHandler"*/)
    public void emailConsumer3(String message) {
        try {
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

            emailSenderService.sendEmail(emailDto);

        } catch (Exception e) {
            log.error(e);

        }
    }


}
