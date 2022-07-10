//package com.example.cs195tennis.Util;
//
//import org.jooq.Converter;
//import org.jooq.DataType;
//import org.jooq.impl.SQLDataType;
//
//import java.time.YearMonth;
//import java.util.*;
//
//import static org.jooq.impl.SQLDataType.BLOB;
//
//public class IntegerConverter implements Converter<Object, Integer> {
//
//
//    public static final Converter<Object, Integer> OBJECT_INTEGER_CONVERTER
//            = new IntegerConverter();
//
//    public static final Converter<Object[], Integer[]> OBJECT_INTEGER_CONVERTER_ARR
//            = OBJECT_INTEGER_CONVERTER.forArrays();
//
//    public static final DataType<Integer> INTEGER_DATA_TYPE
//            = BLOB.asConvertedDataType(OBJECT_INTEGER_CONVERTER);
//
//
//    @Override
//    public Integer from(Object databaseObject) {
//        return null;
//    }
//
//    @Override
//    public Object to(Integer u) {
//
//        if (u != null) {
//            return u;
//        }
//        return null;
//    }
//
//    @Override
//    public Class<Object> fromType() {
//        return Object.class;
//    }
//
//    @Override
//    public Class<Integer> toType() {
//        return Integer.class;
//    }
//
//    @Override
//    public Converter<Integer, Object> inverse() {
//        return Converter.super.inverse();
//    }
//
//    @Override
//    public <X> Converter<Object, X> andThen(Converter<? super Integer, X> converter) {
//        return Converter.super.andThen(converter);
//    }
//
//    public static void objectToInt() {
//        Converter<String, Long> c1 =
//                Converter.ofNullable(String.class, Long.class, Long::parseLong, Object::toString);
//
//        Converter<Long, Integer> c2 =
//                Converter.ofNullable(Long.class, Integer.class, Number::intValue, Number::longValue);
//
//        DataType<Long> d1 = SQLDataType.VARCHAR.asConvertedDataType(c1);
//
//        DataType<Integer> d2 = d1.asConvertedDataType(c2);
//
//        Integer t = (Integer) ((Converter<String, Integer>) d2.getConverter()).from("1");
//    }
//
//}
