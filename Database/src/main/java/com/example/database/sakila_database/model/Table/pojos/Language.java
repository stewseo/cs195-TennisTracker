package com.example.database.sakila_database.model.Table.pojos;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record Language(
        Long languageId,
        String name,
        LocalDateTime lastUpdate
) implements Serializable {

    private static final long serialVersionUID = 1L;

}