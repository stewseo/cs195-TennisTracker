//package com.example.cs195tennis.Data.Table;
//
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.DefaultVisitListener;
//
//import java.util.*;
//
//import static java.lang.System.out;
//import static java.util.Arrays.asList;
//import static org.jooq.Clause.*;
//import static org.jooq.impl.DSL.val;
//import static org.jooq.tools.StringUtils.abbreviate;
//
//
//public class BindValueAbbreviator extends DefaultVisitListener {
//
//    final Integer[] ids;
//
//    public BindValueAbbreviator(Integer... ids) {
//        this.ids = ids;
//    }
//
//    private boolean anyAbbreviations = false;
//
//    void push(VisitContext context) {
//        conditionStack(context).push(new ArrayList<>());
//        whereStack(context).push(false);
//    }
//
//    void pop(VisitContext context) {
//        whereStack(context).pop();
//        conditionStack(context).pop();
//    }
//
//    Deque<List<Condition>> conditionStack(
//            VisitContext context) {
//        Deque<List<Condition>> data = (Deque<List<Condition>>)
//                context.data("conditions");
//
//        if (data == null) {
//            data = new ArrayDeque<>();
//            context.data("conditions", data);
//        }
//
//        return data;
//    }
//
//    Deque<Boolean> whereStack(VisitContext context) {
//        Deque<Boolean> data = (Deque<Boolean>)
//                context.data("predicates");
//
//        if (data == null) {
//            data = new ArrayDeque<>();
//            context.data("predicates", data);
//        }
//
//        return data;
//    }
//    List<Condition> conditions(VisitContext context) {
//        return conditionStack(context).peek();
//    }
//
//    boolean where(VisitContext context) {
//        return whereStack(context).peek();
//    }
//
//    void where(VisitContext context, boolean value) {
//        whereStack(context).pop();
//        whereStack(context).push(value);
//    }
//
//
//    @Override
//    public void clauseStart(VisitContext context) {
//
//        if (context.clause() == SELECT ||
//                context.clause() == UPDATE ||
//                context.clause() == DELETE ||
//                context.clause() == INSERT) {
//            push(context);
//        }
//
//    }
//
//    @Override
//    public void clauseEnd(VisitContext context) {
//
//        // Append all collected predicates to the WHERE
//        // clause if any
//        if (context.clause() == SELECT_WHERE ||
//                context.clause() == UPDATE_WHERE ||
//                context.clause() == DELETE_WHERE) {
//            List<Condition> conditions =
//                    conditions(context);
//
//            if (conditions.size() > 0) {
//                context.context()
//                        .formatSeparator()
//                        .keyword(where(context)
//                                ? "and"
//                                : "where"
//                        )
//                        .sql(' ');
//
//                context.context().visit(
//                        DSL.condition(Operator.AND, conditions)
//                );
//            }
//        }
//
//
////        @Override
////        public void visitStart (VisitContext context){
////            int maxLength = 5;
////            if (context.renderContext() != null) {
////                QueryPart part = context.queryPart();
////
////                if (part instanceof Param<?>) {
////                    Param<?> param = (Param<?>) part;
////                    Object value = param.getValue();
////
////                    // If the bind value is a String (or Clob) of a given length, abbreviate it
////                    //  using commons-lang's StringUtils.abbreviate()
////                    if (value instanceof String && ((String) value).length() > maxLength) {
////                        anyAbbreviations = true;
////
////                        context.queryPart(val(abbreviate((String) value, maxLength)));
////                    }
////                    // If the bind value is a byte[] (or Blob) of a given length, abbreviate it
////                    // e.g. by removing bytes from the array
////                    else if (value instanceof byte[] && ((byte[]) value).length > maxLength) {
////                        anyAbbreviations = true;
////
////                        // ... and replace it in the current rendering context (not in the Query)
////                        context.queryPart(val(Arrays.copyOf((byte[]) value, maxLength)));
////                    }
////                }
////            }
////        }
//
//        <E> void pushConditions(
//                VisitContext context,
//                Table<?> table,
//                Field<E> field,
//                E... values) {
//
//            // Check if we're visiting the given table
//            if (context.queryPart() == table) {
//                List<Clause> clauses = clauses(context);
//
//                // ... and if we're in the context of the current
//                //  subselect's FROM clause
//                if (clauses.contains(SELECT_FROM) ||
//                        clauses.contains(UPDATE_UPDATE) ||
//                        clauses.contains(DELETE_DELETE)) {
//
//                    // If we're declaring a TABLE_ALIAS...
//                    // (e.g. "ACCOUNTS" as "a")
//                    if (clauses.contains(TABLE_ALIAS)) {
//                        QueryPart[] parts = context.queryParts();
//
//                        // ... move up the QueryPart visit path to find the
//                        // defining aliased table, and extract the aliased
//                        // field from it. (i.e. the "a" reference)
//                        for (int i = parts.length - 2; i >= 0; i--) {
//                            if (parts[i] instanceof Table) {
//                                field = ((Table<?>) parts[i]).field(field);
//                                break;
//                            }
//                        }
//                    }
//
//                    // Push a condition for the field of the
//                    // (potentially aliased) table
//                    conditions(context).add(field.in(values));
//                }
//            }
//        }
//        @Override
//        public void visitEnd (VisitContext context){
//
//            // We'll see what this means in a bit...
//            pushConditions(context, ACCOUNTS,
//                    ACCOUNTS.ID, ids);
//            pushConditions(context, TRANSACTIONS,
//                    TRANSACTIONS.ACCOUNT_ID, ids);
//
//            // Check if we're rendering any condition within
//            // the WHERE clause In this case, we can be sure
//            // that jOOQ will render a WHERE keyword
//            if (context.queryPart() instanceof Condition) {
//                List<Clause> clauses = clauses(context);
//
//                if (clauses.contains(SELECT_WHERE) ||
//                        clauses.contains(UPDATE_WHERE) ||
//                        clauses.contains(DELETE_WHERE)) {
//                    where(context, true);
//                }
//            }
//        }
//
//        List<Clause> clauses (VisitContext context){
//            List<Clause> result = asList(context.clauses());
//            int index = result.lastIndexOf(SELECT);
//
//            if (index > 0)
//                return result.subList(index, result.size() - 1);
//            else
//                return result;
//        }
//
//}
//
//
