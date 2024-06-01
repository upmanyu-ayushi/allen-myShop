package com.allenDigital.allenDigital.repository;

import com.allenDigital.allenDigital.entity.User;
import com.allenDigital.allenDigital.entity.UserDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDealRepository extends JpaRepository<UserDeal, Long> {
    List<UserDeal> findAllByUsers(User user);
}
