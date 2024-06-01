package com.allenDigital.allenDigital.repository;

import com.allenDigital.allenDigital.dto.DealDTO;
import com.allenDigital.allenDigital.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
