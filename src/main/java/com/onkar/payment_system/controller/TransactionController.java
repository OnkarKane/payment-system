package com.onkar.payment_system.controller;

import com.onkar.payment_system.entity.Transaction;
import com.onkar.payment_system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public List<Transaction> getUserTransactions(@PathVariable Long id) {
        return transactionRepository.findBySenderIdOrReceiverId(id, id);
    }
}