package com.rofat.MySQLWorkBench.user.Repo;

import com.rofat.MySQLWorkBench.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getUserByUserId(Integer id);
    User getUserByMaId(Integer id);
    Boolean existsByUserId(Integer id);

}
