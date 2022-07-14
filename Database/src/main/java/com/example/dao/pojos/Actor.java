package com.example.dao.pojos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.database.sakila_database.model.Tables.ACTOR;

public record Actor(
        Long actorId,
        String firstName,
        String lastName,
        LocalDateTime lastUpdate
)   implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

//
//    public void pojos() {
//
//        List<com.example.database.sakila_database.model.Table.Actor> actors =
//                ctx.selectFrom(ACTOR)
//                        .where(ACTOR.ACTOR_ID.lt(4L))
//                        .fetchInto(com.example.database.sakila_database.model.Table.Actor.class);
//
//        actors.forEach(System.out::println);
//    }
}