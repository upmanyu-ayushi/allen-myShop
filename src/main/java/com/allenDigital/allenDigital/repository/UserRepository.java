package com.allenDigital.allenDigital.repository;

import com.allenDigital.allenDigital.dto.UserDTO;
import com.allenDigital.allenDigital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
