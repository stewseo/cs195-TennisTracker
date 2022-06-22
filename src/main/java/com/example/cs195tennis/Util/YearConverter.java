//package com.example.cs195tennis.Util;
//
//import java.time.Year;
//
//public class YearConverter implements AttributeConverter<Year, Integer> {
//
//        @Override
//        public Integer convertToDatabaseColumn(Year attribute) {
//            return attribute == null ? null : attribute.getValue();
//        }
//
//        @Override
//        public Year convertToEntityAttribute(Integer dbData) {
//            return dbData == null ? null : Year.of(dbData);
//        }
//    }
//
