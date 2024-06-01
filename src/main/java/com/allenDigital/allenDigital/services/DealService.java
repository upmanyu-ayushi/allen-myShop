package com.allenDigital.allenDigital.services;

import com.allenDigital.allenDigital.constants.CommonConstants;
import com.allenDigital.allenDigital.dto.DealDTO;
import com.allenDigital.allenDigital.entity.Deal;
import com.allenDigital.allenDigital.entity.User;
import com.allenDigital.allenDigital.entity.UserDeal;
import com.allenDigital.allenDigital.exceptions.EntityNotFound;
import com.allenDigital.allenDigital.exceptions.UserDealLimitExceeded;
import com.allenDigital.allenDigital.repository.DealRepository;
import com.allenDigital.allenDigital.repository.UserDealRepository;
import com.allenDigital.allenDigital.utils.DtoToEntityConverter;
import com.allenDigital.allenDigital.utils.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// The task is to create APIs to enable the following operations
// Create a deal with the price, end time(optional) and number of items to be sold as part of the deal.
// End a deal.
// Update a deal to increase the number of items or end-time.
// Claim a deal (Basically, creating an order. No need to take care of item price or payment).
@Service
public class DealService {

    @Autowired
    DealRepository dealRepository;

    @Autowired
    UserDealRepository userDealRepository;

    public Deal create(Deal deal) {
//        Deal dealEntity = DtoToEntityConverter.getDealEntity(deal);
        return dealRepository.save(deal);
    }


    public void update(Long id, Deal deal) throws EntityNotFound {
        Optional<Deal> dealEntity = dealRepository.findById(id);

        if (!dealEntity.isPresent()) {
            throw new EntityNotFound(CommonConstants.DEAL);
        }

        dealRepository.save(deal);
    }

    public void endDeal(Long id) throws EntityNotFound {

        Optional<Deal> deal = dealRepository.findById(id);

        if (!deal.isPresent()) {
            throw new EntityNotFound(CommonConstants.DEAL);
        }

        deal.get().setEnded(true);
        dealRepository.save(deal.get());

    }

    @Transactional
    public void claim(Long id, User user) throws EntityNotFound, UserDealLimitExceeded {

        Optional<Deal> deal = dealRepository.findById(id);

        if (!deal.isPresent()) {
            throw new EntityNotFound(CommonConstants.DEAL);
        }

        List<UserDeal> userDeals = userDealRepository.findAllByUsers(user);

        if (userDeals.isEmpty()) {

            UserDeal userDeal = new UserDeal();
            userDeal.setDeal(deal.get());
            userDeal.setUser(user);

            userDealRepository.save(userDeal);

            deal.get().setAvailableItems(deal.get().getAvailableItems() - 1);
            dealRepository.save(deal.get());

        } else {
            throw new UserDealLimitExceeded();
        }

    }




}
