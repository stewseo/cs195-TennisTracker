//package com.example.application;
//
//import com.example.database.sakila_database.AbstractIntegrationTest;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//
//import static org.rnorth.visibleassertions.VisibleAssertions.assertEquals;
//import static org.rnorth.visibleassertions.VisibleAssertions.info;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class MainControllerTest extends AbstractIntegrationTest {
//
//        @Autowired
//        TestRestTemplate restTemplate;
//
//        @Autowired
//        DemoRepository demoRepository;
//
//        @Test
//        public void simpleTest() {
//            String fooResource = "/foo";
//
//            info("putting 'bar' to " + fooResource);
//            restTemplate.put(fooResource, "bar");
//
//            assertEquals("value is set", "bar", restTemplate.getForObject(fooResource, String.class));
//        }
//
//        @Test
//        public void simpleJPATest() {
//            DemoEntity demoEntity = new DemoEntity();
//            demoEntity.setValue("Some value");
//            demoRepository.save(demoEntity);
//
//            DemoEntity result = restTemplate.getForObject("/" + demoEntity.getId(), DemoEntity.class);
//
//            assertEquals("value is set", "Some value", result.getValue());
//        }
//
//    }
//
