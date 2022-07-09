package Database.Model.SakilaModel.QueryParts;

import org.jooq.*;
import org.jooq.impl.CustomQueryPart;
import org.jooq.impl.DSL;


import static com.example.cs195tennis.VerifyDataIntegrity.VerifyDatabase.getColumnValue;
import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DefaultBinding.binding;

public class CustQueryPart extends CustomQueryPart {

    private QueryPart queryPart;

    public CustQueryPart() {}

    @Override
    public void accept(Context<?> ctx) {
        out.println("ctx: " + ctx);
    }

    //==================================================================================
    //             Constructor to build query in steps
    //==================================================================================
    public CustQueryPart(String selectString, String fromString, String where, String condition, String having, String grouping) {
        SelectField<?>[] select = {
                DSL.field(selectString),
                count()
        };

        Table<?>[] from = {
                DSL.table(fromString)
        };

        GroupField[] groupBy = {
                DSL.field(grouping)
        };

        SortField<?>[] orderBy = {
                count().desc()
        };

    }

}
