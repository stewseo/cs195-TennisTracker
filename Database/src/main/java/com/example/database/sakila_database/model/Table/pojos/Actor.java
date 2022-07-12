package com.example.database.sakila_database.model.Table.pojos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record Actor(
        Long actorId,
        String firstName,
        String lastName,
        LocalDateTime lastUpdate
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}