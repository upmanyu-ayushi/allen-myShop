package com.allenDigital.allenDigital.controllers;

import com.allenDigital.allenDigital.dto.DealDTO;
import com.allenDigital.allenDigital.dto.UserDTO;
import com.allenDigital.allenDigital.entity.Deal;
import com.allenDigital.allenDigital.entity.User;
import com.allenDigital.allenDigital.exceptions.EntityNotFound;
import com.allenDigital.allenDigital.exceptions.UserDealLimitExceeded;
import com.allenDigital.allenDigital.services.DealService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/deal")
public class DealController {

    @Autowired
    DealService dealService;

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestBody Deal deal) {
        return ResponseEntity.ok("test");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Deal deal) {
        try {
            return ResponseEntity.ok(dealService.create(deal));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long dealId, @RequestBody Deal deal) {
        try {
            dealService.update(dealId, deal);

        } catch (EntityNotFound e) {
            ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<?> endDeal(@PathVariable(value = "id") Long dealId) {
        try {
            dealService.endDeal(dealId);

        } catch (EntityNotFound e) {
            ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/claim/{id}")
    public ResponseEntity<?> claimDeal(@PathVariable Long id, @RequestBody User user) {

        try {
            dealService.claim(id, user);

        } catch (EntityNotFound e) {
            ResponseEntity.badRequest().build();
        } catch (UserDealLimitExceeded e) {
            ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();

    }
}
