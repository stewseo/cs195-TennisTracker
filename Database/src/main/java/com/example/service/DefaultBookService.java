package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.jooq.Table;
import org.springframework.transaction.annotation.Transactional;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Service
public class DefaultBookService implements BookService {
    static Table<?> BOOK = table("book");
    @Autowired
    DSLContext dsl;

    @Override
    @Transactional
    public void create(int id, int authorId, String title) {

        // This method has a "bug". It creates the same book twice. The second insert
        // should lead to a constraint violation, which should roll back the whole transaction
        for (int i = 0; i < 2; i++)
            dsl.insertInto(BOOK).set(field("book.book_id"), id).set(field("book.book_id"), authorId).set(field("book.book_title"), title).execute();
    }
}