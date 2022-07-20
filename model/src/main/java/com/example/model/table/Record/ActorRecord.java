package com.example.model.table.Record;


import com.example.model.table.Actor;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import java.io.Serial;
import java.time.LocalDateTime;

public class ActorRecord extends UpdatableRecordImpl<ActorRecord> implements Record4<Long, String, String, LocalDateTime> {

        @Serial
        private static final long serialVersionUID = 1L;

        public void setActorId(Long value) {
            set(0, value);
        }

        public Long getActorId() {
            return (Long) get(0);
        }

        public void setFirstName(String value) {
            set(1, value);
        }


        public String getFirstName() {
            return (String) get(1);
        }

        public void setLastName(String value) {
            set(2, value);
        }


        public String getLastName() {
            return (String) get(2);
        }


        public void setLastUpdate(LocalDateTime value) {
            set(3, value);
        }


        public LocalDateTime getLastUpdate() {
            return (LocalDateTime) get(3);
        }

        // -------------------------------------------------------------------------
        // Primary key information
        // -------------------------------------------------------------------------

        @Override
        public Record1<Long> key() {
            return (Record1) super.key();
        }

        // -------------------------------------------------------------------------
        // Record4 type implementation
        // -------------------------------------------------------------------------

        @Override
        public Row4<Long, String, String, LocalDateTime> fieldsRow() {
            return (Row4) super.fieldsRow();
        }

        @Override
        public Row4<Long, String, String, LocalDateTime> valuesRow() {
            return (Row4) super.valuesRow();
        }

        @Override
        public Field<Long> field1() {
            return com.example.model.table.Actor.ACTOR.ACTOR_ID;
        }

        @Override
        public Field<String> field2() {
            return com.example.model.table.Actor.ACTOR.FIRST_NAME;
        }

        @Override
        public Field<String> field3() {
            return com.example.model.table.Actor.ACTOR.LAST_NAME;
        }

        @Override
        public Field<LocalDateTime> field4() {
            return com.example.model.table.Actor.ACTOR.LAST_UPDATE;
        }

        @Override
        public Long component1() {
            return getActorId();
        }

        @Override
        public String component2() {
            return getFirstName();
        }

        @Override
        public String component3() {
            return getLastName();
        }

        @Override
        public LocalDateTime component4() {
            return getLastUpdate();
        }

        @Override
        public Long value1() {
            return getActorId();
        }

        @Override
        public String value2() {
            return getFirstName();
        }

        @Override
        public String value3() {
            return getLastName();
        }

        @Override
        public LocalDateTime value4() {
            return getLastUpdate();
        }

        @Override
        public ActorRecord value1(Long value) {
            setActorId(value);
            return this;
        }

        @Override
        public ActorRecord value2(String value) {
            setFirstName(value);
            return this;
        }

        @Override
        public ActorRecord value3(String value) {
            setLastName(value);
            return this;
        }

        @Override
        public ActorRecord value4(LocalDateTime value) {
            setLastUpdate(value);
            return this;
        }

        @Override
        public ActorRecord values(Long value1, String value2, String value3, LocalDateTime value4) {
            value1(value1);
            value2(value2);
            value3(value3);
            value4(value4);
            return this;
        }

        // -------------------------------------------------------------------------
        // Constructors
        // -------------------------------------------------------------------------

        public ActorRecord() {
            super(com.example.model.table.Actor.ACTOR);
        }

        public ActorRecord(Long actorId, String firstName, String lastName, LocalDateTime lastUpdate) {
            super(com.example.model.table.Actor.ACTOR);

            setActorId(actorId);
            setFirstName(firstName);
            setLastName(lastName);
            setLastUpdate(lastUpdate);
        }

        public ActorRecord(Long actorId, String firstName, String lastName) {
            super(com.example.model.table.Actor.ACTOR);

            setActorId(actorId);
            setFirstName(firstName);
            setLastName(lastName);
        }

    public ActorRecord(com.example.model.table.pojos.Actor value) {
        super(com.example.model.table.Actor.ACTOR);

        if (value != null) {
            setActorId(value.actorId());
            setFirstName(value.firstName());
            setLastName(value.lastName());
            setLastUpdate(value.lastUpdate());
        }
    }
}

