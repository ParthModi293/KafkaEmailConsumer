package com.email.kafkaemailconsumer.controller;


import com.email.kafkaemailconsumer.service.EmailUtil;
import org.communication.dto.EmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.common.common.*;

@RestController
@RequestMapping("/restEmail")
public class EmailController {

    private final EmailUtil emailUtil;

    public EmailController(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @PostMapping("/send")
    public ResponseEntity<ResponseBean<Void>> restEmailCall(@RequestBody EmailDto emailDto) {
        ResponseBean<Void> responseBean = emailUtil.sendEmailRestCall(emailDto);
        return new ResponseEntity<>(responseBean, responseBean.getRStatus());
    }

}
