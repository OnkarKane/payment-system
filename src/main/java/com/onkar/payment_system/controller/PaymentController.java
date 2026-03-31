package com.onkar.payment_system.controller;

import com.onkar.payment_system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long sender,
                           @RequestParam Long receiver,
                           @RequestParam Double amount) {

        return paymentService.transfer(sender, receiver, amount);
    }

}