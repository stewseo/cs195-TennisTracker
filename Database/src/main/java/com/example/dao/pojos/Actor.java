package com.example.dao.pojos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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
//        List<Actor> actors =
//                ctx.selectFrom(ACTOR)
//                        .where(ACTOR.ACTOR_ID.lt(4L))
//                        .fetchInto(Actor.class);
//
//        actors.forEach(System.out::println);
//    }
}