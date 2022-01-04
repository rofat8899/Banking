package com.rofat.MySQLWorkBench.user.Repo;

import com.rofat.MySQLWorkBench.user.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccRepo extends JpaRepository<UserAccount,Integer>{
    List<UserAccount> getUserAccountByMaId(Integer id);
    UserAccount getUserAccountByAccountNumberAndMaId(int accountNumber,int maId);
    UserAccount getUserAccountByAccountNumber(int accountNumber);
    void deleteUserAccountByAccountNumber(int accountNumber);

}

