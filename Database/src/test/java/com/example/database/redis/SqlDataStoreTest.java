package com.example.database.redis;

import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.VolumesFrom;
import org.jooq.exception.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.getLogger;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

@Testcontainers
public class SqlDataStoreTest {
    static StringBuilder logToFile;

    @Container
    PostgreSQLContainer<?> postGresTestContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withDatabaseName("postgres");


    @Test
    void runDummyQuery() throws SQLException {


        SqlDataStore sqlDataStore =
                new SqlDataStore(postGresTestContainer.getJdbcUrl(),
                        postGresTestContainer.getUsername(),
                        postGresTestContainer.getPassword());

        logToFile = new StringBuilder("Log Info: " + postGresTestContainer.getJdbcUrl());
        //=================================================================================================
        //                  Write jdbc driver, login name, password, database name to txt file
        //=================================================================================================
        verifyLogin();
        //=================================================================================================
        //                          Network information
        //=================================================================================================
        verifyNetwork();

        //=================================================================================================
        //                        Environment information
        //=================================================================================================
        verifyEnvironment();

        int result = sqlDataStore.runDummyQuery();

        //=================================================================================================
        //                    Test that postgres container has a record
        //=================================================================================================
        if (result != 1) {
            logToFile.append("\nTest Query String: ").append(postGresTestContainer.getTestQueryString())
                    .append("\nContainer ID: ").append(postGresTestContainer.getContainerId());
        }

        //=================================================================================================
        //                                  Write all logs to file
        //=================================================================================================
        logToFile.append("\npostGres Testcontainers log:\n ").append(postGresTestContainer.getLogs());
        writeToTxt(logToFile.toString());
        Assertions.assertEquals(1, result);
    }

//    private Class<? extends PostgreSQLContainer<?>> clazz;
//
//    public SqlDataStoreTest(Class<? extends PostgreSQLContainer<?>> clazz) {
//        this.clazz = clazz;
//    }
//

    private void verifyContainer() {
        if (postGresTestContainer.getContainerName() != null) {
            logToFile.append("\npostGres Testcontainers log:\n ").append(postGresTestContainer.getLogs());
        }
    }


    //=================================================================================================
    //                                  environment variables passed to the container.
    //=================================================================================================
    private void verifyEnvironment(){
        if(postGresTestContainer.getEnv() != null) {
            logToFile.append("\nEnvironment:\n ").append(postGresTestContainer.getLogs());
        }
        if(postGresTestContainer.getEnvMap() != null) {
            logToFile.append("\nEnvironment Map:\n ").append(postGresTestContainer.getLogs());
        }
    }

    //=================================================================================================
    //                                   network settings
    //=================================================================================================
    private void verifyNetwork(){
        if(postGresTestContainer.getBoundPortNumbers() != null) {
            logToFile.append("\nBound Port Numbers:\n ").append(postGresTestContainer.getBoundPortNumbers());
        }

        if(postGresTestContainer.getNetwork() != null) {
            logToFile.append("\nNetwork:\n ").append(postGresTestContainer.getNetwork());
        }

        if(postGresTestContainer.getExtraHosts() != null) {
            logToFile.append("\nExtraHosts:\n ").append(postGresTestContainer.getNetwork());
        }

        if(postGresTestContainer.getExtraHosts() != null) {
            logToFile.append("\nNetwork:\n ").append(postGresTestContainer.getNetwork());
        }
        if(postGresTestContainer.getNetworkMode() != null) {
            logToFile.append("\ngetNetworkMode:\n ").append(postGresTestContainer.getNetworkMode());
        }

        if(postGresTestContainer.getNetworkAliases() != null) {
            logToFile.append("\nNetworkAliases:\n ").append(postGresTestContainer.getNetworkAliases());
        }

    }


    private void verifyLogin() {
        logToFile.append("\npostgres testcontainers login:\n ");
        Stream.of(
                        postGresTestContainer.getJdbcUrl(),
                        postGresTestContainer.getUsername(),
                        postGresTestContainer.getPassword(),
                        postGresTestContainer.getDatabaseName())
                .filter(Objects::nonNull)
                .forEach(field -> logToFile.append("\n").append(field));


//        if(postGresTestContainer.getJdbcUrl() != null) {
//            logToFile.append("\nJdbcUrl: ").append(postGresTestContainer.getNetwork());
//        }
//
//        if(postGresTestContainer.getUsername() != null) {
//                logToFile.append("\nUsername: ").append(postGresTestContainer.getNetwork());
//        }
//        if(postGresTestContainer.getPassword() != null) {
//                logToFile.append("\nPassword: ").append(postGresTestContainer.getNetwork());
//        }
//
//        if(postGresTestContainer.getDatabaseName() != null){
//                logToFile.append("\nDatabaseName: ").append(postGresTestContainer.getNetwork());
//        }

    }


    public void checkNull(Class<?> testContainerClass) throws IllegalAccessException {
        Arrays.stream(testContainerClass.getDeclaredFields()).filter(Objects::nonNull)
                .forEach(postGresContainerField-> {

                });
    }

    private void verifyImage() {
        if(postGresTestContainer.getImage() != null){
            logToFile.append("\npostGres getImage:\n ").append(postGresTestContainer.getLogs());
        }
        if(postGresTestContainer.getLabels() != null){
            logToFile.append("\npostGres getLabels:\n ").append(postGresTestContainer.getLogs());
        }
        if(postGresTestContainer.getCommandParts() != null){
            logToFile.append("\npostGres getCommandParts:\n ").append(postGresTestContainer.getLogs());
        }
        if(postGresTestContainer.getVolumesFroms() != null){
            logToFile.append("\npostGres getVolumesFroms:\n ").append(postGresTestContainer.getLogs());
        }
        if(postGresTestContainer.isPrivilegedMode()){
            logToFile.append("\npostGres isPrivilegedMode:\n ").append(postGresTestContainer.getLogs());
        }
    }
    
    private void writeToTxt(String testContainerLog) {
            File file = new File("testcontainer_log.txt");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write(testContainerLog);

        } catch (IOException | java.io.IOException e) {
            e.printStackTrace();
        }
    }


    //    public GenericContainer containerWithLogWait = new GenericContainer(DockerImageName.parse("postgres:latest"))
//            .withExposedPorts(6379)
//            .waitingFor(
//                    Wait.forLogMessage(".*Ready to accept connections.*\\n", 3)
//            );
}
