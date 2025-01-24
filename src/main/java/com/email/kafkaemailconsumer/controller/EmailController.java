package com.email.kafkaemailconsumer.controller;

import com.email.kafkaemailconsumer.service.EmailUtil;
import org.common.common.ResponseBean;
import org.communication.dto.EmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restEmail")
public class EmailController {

    private final EmailUtil emailUtil;

    public EmailController(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @PostMapping("/send")
    public ResponseEntity<ResponseBean<?>> restEmailCall(EmailDto emailDto) {

        ResponseBean<Void> voidResponseBean = emailUtil.sendEmailRestCall(emailDto);
        return new ResponseEntity<>(voidResponseBean,voidResponseBean.getRStatus());



    }

}
