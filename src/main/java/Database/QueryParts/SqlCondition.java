//package Data.QueryPartsImpl;
//
//import org.jooq.impl.SelectQueryImpl;
//
//import static org.jooq.impl.DSL.select;
//
//public class SelectQueryImp implements SelectQueryImpl<Record> {
//
////    public Condition condition(Query query, Table<?> table) {
////        Condition result = noCondition();
////
////        if (query.getParams() != null)
////            result = result.and(table.TITLE.like("%" + request.getParameter("title") + "%"));
////
////        if (query.getParams() != null)
////            result = result.and(table.AUTHOR_ID.in(
////                    select(AUTHOR.ID).from(AUTHOR).where(
////                            AUTHOR.FIRST_NAME.like("%" + request.getParameter("author") + "%")
////                                    .or(AUTHOR.LAST_NAME .like("%" + request.getParameter("author") + "%"))
////                    )
////            ));
////
////        return result;
////    }
//}
