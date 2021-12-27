package com.rofat.MySQLWorkBench.user.Repo;

import com.rofat.MySQLWorkBench.user.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepo extends JpaRepository<UserContact,Integer> {
    UserContact findUserContactByMaId(Integer id);
}
