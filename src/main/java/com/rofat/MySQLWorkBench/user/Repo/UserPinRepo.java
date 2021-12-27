package com.rofat.MySQLWorkBench.user.Repo;

import com.rofat.MySQLWorkBench.user.model.UserPin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPinRepo extends JpaRepository<UserPin,Integer> {
}
