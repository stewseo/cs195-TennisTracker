package com.example.dao.pojos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record Category(
            Long categoryId,
            String name,
            LocalDateTime lastUpdate
    ) implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
    }
