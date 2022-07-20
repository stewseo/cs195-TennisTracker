package com.example.model.table.pojos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


public record Language(
        Long languageId,
        String name,
        LocalDateTime lastUpdate
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}