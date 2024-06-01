package com.allenDigital.allenDigital.utils;

import com.allenDigital.allenDigital.dto.DealDTO;
import com.allenDigital.allenDigital.entity.Deal;

public class DtoToEntityConverter {

    public static Deal getDealEntity(DealDTO dealDTO) {

        Deal dealEntity = new Deal();
//        dealEntity.setEnded(dealDTO);
        return new Deal();

    }
}
