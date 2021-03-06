//package com.example.cs195tennis.Util;
//
//import java.sql.Connection;
//
//
//    import java.io.IOException;
//import java.io.LineNumberReader;
//import java.io.PrintWriter;
//import java.io.Reader;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ScriptRunner {
//
//        private static final String DEFAULT_DELIMITER = ";";
//        private static final String DELIMITER_LINE_REGEX = "(?i)DELIMITER.+";
//        private static final String DELIMITER_LINE_SPLIT_REGEX = "(?i)DELIMITER";
//
//        private final Connection connection;
//        private final boolean stopOnError;
//        private final boolean autoCommit;
//        private PrintWriter logWriter = new PrintWriter(System.out);
//        private PrintWriter errorLogWriter = new PrintWriter(System.err);
//        private String delimiter = DEFAULT_DELIMITER;
//        private boolean fullLineDelimiter = false;
//
//
//        public ScriptRunner(Connection connection, boolean autoCommit, boolean stopOnError) {
//            this.connection = connection;
//            this.autoCommit = autoCommit;
//            this.stopOnError = stopOnError;
//        }
//
//        public void setDelimiter(String delimiter, boolean fullLineDelimiter) {
//            this.delimiter = delimiter;
//            this.fullLineDelimiter = fullLineDelimiter;
//        }
//
//
//        public void setLogWriter(PrintWriter logWriter) {
//            this.logWriter = logWriter;
//        }
//
//
//        public void setErrorLogWriter(PrintWriter errorLogWriter) {
//            this.errorLogWriter = errorLogWriter;
//        }
//
//
//        public void runScript(Reader reader) throws IOException, SQLException {
//            try {
//                boolean originalAutoCommit = connection.getAutoCommit();
//                try {
//                    if (originalAutoCommit != autoCommit) {
//                        connection.setAutoCommit(autoCommit);
//                    }
//                    runScript(connection, reader);
//                } finally {
//                    connection.setAutoCommit(originalAutoCommit);
//                }
//            } catch (IOException | SQLException e) {
//                throw e;
//            } catch (Exception e) {
//                throw new RuntimeException("Error running script.  Cause: " + e, e);
//            }
//        }
//
//
//        private void runScript(Connection conn, Reader reader) throws IOException, SQLException {
//            StringBuffer command = null;
//            try {
//                LineNumberReader lineReader = new LineNumberReader(reader);
//                String line = null;
//                while ((line = lineReader.readLine()) != null) {
//                    if (command == null) {
//                        command = new StringBuffer();
//                    }
//                    String trimmedLine = line.trim();
//                    if (trimmedLine.startsWith("--")) {
//                        println(trimmedLine);
//                    } else if (trimmedLine.length() < 1 || trimmedLine.startsWith("//")) {
//                        // Do nothing
//                    } else if (trimmedLine.length() < 1 || trimmedLine.startsWith("--")) {
//                        // Do nothing
//                    } else if (!fullLineDelimiter && trimmedLine.endsWith(getDelimiter())
//                            || fullLineDelimiter && trimmedLine.equals(getDelimiter())) {
//
//                        Pattern pattern = Pattern.compile(DELIMITER_LINE_REGEX);
//                        Matcher matcher = pattern.matcher(trimmedLine);
//                        if (matcher.matches()) {
//                            setDelimiter(trimmedLine.split(DELIMITER_LINE_SPLIT_REGEX)[1].trim(),
//                                    fullLineDelimiter);
//                            line = lineReader.readLine();
//                            if (line == null) {
//                                break;
//                            }
//                            trimmedLine = line.trim();
//                        }
//
//                        command.append(line.substring(0, line.lastIndexOf(getDelimiter())));
//                        command.append(" ");
//                        Statement statement = conn.createStatement();
//
//                        println(command);
//
//                        boolean hasResults = false;
//                        if (stopOnError) {
//                            hasResults = statement.execute(command.toString());
//                        } else {
//                            try {
//                                statement.execute(command.toString());
//                            } catch (SQLException e) {
//                                e.fillInStackTrace();
//                                printlnError("Error executing: " + command);
//                                printlnError(e);
//                            }
//                        }
//
//                        if (autoCommit && !conn.getAutoCommit()) {
//                            conn.commit();
//                        }
//
//                        ResultSet rs = statement.getResultSet();
//                        if (hasResults && rs != null) {
//                            ResultSetMetaData md = rs.getMetaData();
//                            int cols = md.getColumnCount();
//                            for (int i = 0; i < cols; i++) {
//                                String name = md.getColumnLabel(i);
//                                print(name + "\t");
//                            }
//                            println("");
//                            while (rs.next()) {
//                                for (int i = 1; i <= cols; i++) {
//                                    String value = rs.getString(i);
//                                    print(value + "\t");
//                                }
//                                println("");
//                            }
//                        }
//
//                        command = null;
//                        try {
//                            if (rs != null) {
//                                rs.close();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            if (statement != null) {
//                                statement.close();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            // Ignore to workaround a bug in Jakarta DBCP
//                        }
//                    } else {
//                        Pattern pattern = Pattern.compile(DELIMITER_LINE_REGEX);
//                        Matcher matcher = pattern.matcher(trimmedLine);
//                        if (matcher.matches()) {
//                            setDelimiter(trimmedLine.split(DELIMITER_LINE_SPLIT_REGEX)[1].trim(),
//                                    fullLineDelimiter);
//                            line = lineReader.readLine();
//                            if (line == null) {
//                                break;
//                            }
//                            trimmedLine = line.trim();
//                        }
//                        command.append(line);
//                        command.append(" ");
//                    }
//                }
//                if (!autoCommit) {
//                    conn.commit();
//                }
//            } catch (SQLException | IOException e) {
//                e.fillInStackTrace();
//                printlnError("Error executing: " + command);
//                printlnError(e);
//                throw e;
//            } finally {
//                conn.rollback();
//                flush();
//            }
//        }
//
//        private String getDelimiter() {
//            return delimiter;
//        }
//
//        private void print(Object o) {
//            if (logWriter != null) {
//                logWriter.print(o);
//            }
//        }
//
//        private void println(Object o) {
//            if (logWriter != null) {
//                logWriter.println(o);
//            }
//        }
//
//        private void printlnError(Object o) {
//            if (errorLogWriter != null) {
//                errorLogWriter.println(o);
//            }
//        }
//
//        private void flush() {
//            if (logWriter != null) {
//                logWriter.flush();
//            }
//            if (errorLogWriter != null) {
//                errorLogWriter.flush();
//            }
//        }
//    }
//
