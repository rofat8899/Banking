package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccRepo extends JpaRepository<UserAccountEntity,Integer>{
    List<UserAccountEntity> getUserAccountByMaId(Integer id);
    UserAccountEntity getUserAccountByAccountNumberAndMaId(int accountNumber,int maId);
    UserAccountEntity getUserAccountByAccountNumber(int accountNumber);
    void deleteUserAccountByAccountNumber(int accountNumber);

}

