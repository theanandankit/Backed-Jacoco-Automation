package com.Rakuten.Automation.Repositories;

import com.Rakuten.Automation.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
