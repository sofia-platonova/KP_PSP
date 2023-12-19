package com.example.back.repository;

import com.example.back.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByLoginAndPassword(String login,String password);
    int countByStatus(String status);
}
