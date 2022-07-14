package com.example.dao.pojos;

import java.util.List;

import static com.example.database.sakila_database.model.Tables.ACTOR;


public class Pojo {

    public void pojos() {
        List<Actor> actors =
                ctx.selectFrom(ACTOR)
                        .where(ACTOR.ACTOR_ID.lt(4L))
                        .fetchInto(Actor.class);

        actors.forEach(System.out::println);
    }
}
