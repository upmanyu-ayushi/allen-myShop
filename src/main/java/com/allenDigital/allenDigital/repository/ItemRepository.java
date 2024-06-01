package com.allenDigital.allenDigital.repository;

import com.allenDigital.allenDigital.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
