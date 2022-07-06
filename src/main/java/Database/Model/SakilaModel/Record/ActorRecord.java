package Database.Model.SakilaModel.Record;


import Database.Model.SakilaModel.Table.ActorTable;
import org.jooq.impl.CustomRecord;

public class ActorRecord extends CustomRecord<ActorRecord> {

        protected ActorRecord() {
            super(ActorTable.ACTOR);
        }

}
