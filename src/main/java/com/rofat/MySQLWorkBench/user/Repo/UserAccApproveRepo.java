package com.rofat.MySQLWorkBench.user.Repo;

import com.rofat.MySQLWorkBench.user.model.UserAccountApprove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccApproveRepo extends JpaRepository<UserAccountApprove,Integer> {
    UserAccountApprove getUserAccountByAccountNumber(int accountNumber);
}
