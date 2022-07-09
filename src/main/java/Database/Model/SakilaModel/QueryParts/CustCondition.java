package Database.Model.SakilaModel.QueryParts;

import org.jooq.Context;
import org.jooq.impl.CustomCondition;

import static java.lang.System.out;

public class CustCondition extends CustomCondition {


    @Override
    public void accept(Context<?> ctx) {
        out.println("Custom Condition: " + ctx);
    }
}
