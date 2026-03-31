package com.onkar.payment_system.service;

import com.onkar.payment_system.entity.Transaction;
import com.onkar.payment_system.entity.User;
import com.onkar.payment_system.repository.TransactionRepository;
import com.onkar.payment_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public String transfer(Long senderId, Long receiverId, Double amount) {

        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Cannot transfer to self");
        }

        if (amount <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        Transaction txn = new Transaction();
        txn.setSenderId(senderId);
        txn.setReceiverId(receiverId);
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());

        transactionRepository.save(txn);

        return "Transfer successful";
    }
}
