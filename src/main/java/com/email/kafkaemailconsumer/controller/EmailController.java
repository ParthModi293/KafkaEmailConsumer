package com.email.kafkaemailconsumer.controller;


import com.email.kafkaemailconsumer.service.EmailUtil;
import lombok.extern.log4j.Log4j2;
import org.communication.dto.EmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.common.common.*;

@RestController
@RequestMapping("/email")
@CrossOrigin
@Log4j2
public class EmailController {

    private final EmailUtil emailUtil;

    public EmailController(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @PostMapping("/send")
    public ResponseEntity<ResponseBean<Void>> sendEmail(@RequestBody EmailDto emailDto) {
        log.info("Send email (rest call): {}", emailDto);
        ResponseBean<Void> responseBean = emailUtil.sendEmail(emailDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

}
