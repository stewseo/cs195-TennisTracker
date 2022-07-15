//package com.example.database.util;
//
//public interface Tools {
//
//
//}
//    public void alterColumnDataType(Table<?> table, Field<?> field, DataType<?> dataType){
//
//        ctx()
//                .alterTable(table)
//                .alter(field)
//                .set(dataType)
//                .execute();
//
//    }

//    public static List<List<String>> getRecordsFromCsv(String pathToCsv) throws IOException {
//        Result<Record> rs = ctx().newResult();
//
//        List<String> paths =
//                java.nio.file.Files
//                        .walk(Paths.get(pathToCsv))
//                        .filter(Files::isRegularFile)
//                        .map(Path::toString)
//                        .toList();
//
//        return dataHandeler.parseCsvToListString(paths.get(0));
//
//    }
//
//

//    public static <T> void modifyTableStatistics(Result<Record> rs) {
//        if(rs.isEmpty()){
//            return;
//        }
//        out.println("number records: " + rs.size());
//        if (StatisticsListener.STATISTICS.keySet().size() > 0) {
//            System.out.println("statistics key set: " + StatisticsListener.STATISTICS.keySet());
//
//            if (StatisticsListener.STATISTICS.entrySet().size() > 0) {
//                System.out.println("entry set: " + StatisticsListener.STATISTICS.entrySet());
//            }
//            Arrays.stream(ExecuteType.values()).forEach(type -> {
//                out.println(type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//            });
//        }
//    }


