package com.example.dao.pojos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record Address(
        Long addressId,
        String address,
        String address2,
        String district,
        Long cityId,
        String postalCode,
        String phone,
        LocalDateTime lastUpdate
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}