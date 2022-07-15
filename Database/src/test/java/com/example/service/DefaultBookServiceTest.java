package com.example.service;

import com.example.database.Application;
import org.aspectj.lang.annotation.After;
import org.jooq.Table;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.database.TestDatabase.ctx;
import static com.example.service.DefaultBookService.BOOK;
import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;


class DefaultBookServiceTest {

    @SpringBootTest(classes = Application.class)
    public class QueryTest {

        @Autowired DSLContext create;

//
//        @Test
//        public void pojos() {
//
//            List<Book> actors =
//                    ctx.selectFrom(ACTOR)
//                            .where(ACTOR.ACTOR_ID.lt(4L))
//                            .fetchInto(Actor.class);
//
//            actors.forEach(System.out::println);
//        }
//
//        @Test
//        public void daos() {
//
//            ActorDao dao = new ActorDao(ctx.configuration());
//            dao.insert(
//                    new Actor(201L, "John", "Doe", null),
//                    new Actor(202L, "Jane", "Smith", null)
//            );
//
//            dao.fetchByActorId(201L, 202L).forEach(System.out::println);
//        }



        @Test
        public void testJoin() throws Exception {

            record BookToBookStore(String title) {}

            Table<?> b = table("book").as("b");
            Table<?> a =  table("author").as("a");
            Table<?> s = table("book_store").as("s");
            Table<?> t = table("BOOK_TO_BOOK_STORE").as("t");

            Result<Record3<Object, Object, Integer>> result = create.select(field("author.first_name"),
                            field("author.last_name"),
                            countDistinct(s.field("name")))
                    .from(a).join(b)
                    .on(field("b.author_id").equal(field("a.author_id")))
                    .join(t).on(field("t.book_id").equal(field("b.book_id")))
                    .join(s)
                    .on(field("t.book_store_name").equal("s.NAME")).groupBy(field("a.FIRST_NAME"), field("a.LAST_NAME"))
                    .orderBy(countDistinct(field("s.NAME")).desc())
                    .fetch();

            assertEquals(2, result.size());
            assertEquals("Paulo", result.getValue(0, field("a.FIRST_NAME")));
            assertEquals("George", result.getValue(1, field("a.FIRST_NAME")));

            assertEquals("Coelho", result.getValue(0, field("a.LAST_NAME")));
            assertEquals("Orwell", result.getValue(1, field("a.LAST_NAME")));

            assertEquals(Integer.valueOf(3), result.getValue(0, countDistinct(field("s.NAME"))));
            assertEquals(Integer.valueOf(2), result.getValue(1, countDistinct(field("s.NAME"))));
        }

        @Test
        public void testActiveRecords() throws Exception {
            Result<?> result = create.selectFrom(BOOK).orderBy(field("book.book_id")).fetch();

            assertEquals(4, result.size());
            assertEquals(asList(1, 2, 3, 4), result.getValues(0));
        }
    }
}