package TestData.TestDatabase;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestJdbcJooq {

    @Mock
    Iterator<String> i;

    Comparable<String> c;

    int userId = 100;

    public TestJdbcJooq() {}

    @Test
    void testMoreThanOneReturnValue() {
        when(i.next()).thenReturn("Burrito").thenReturn("Pizza");
        String result = i.next() + " " + i.next();

        assertEquals("Burrito Pizza", result);
    }


    @Test
    void testReturnValueDependentOnMethodParameter(@Mock Comparable<String> c) {
        when(c.compareTo("Burrito")).thenReturn(1);
        when(c.compareTo("Pizza")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Burrito"));
        assertEquals(2, c.compareTo("Pizza"));
    }

    @Test
    void testReturnValueInDependentOnMethodParameter2(@Mock Comparable<Integer> c )  {
        when(c.compareTo(isA(Integer.class))).thenReturn(0);

        assertEquals(0, c.compareTo(4));
    }


}
