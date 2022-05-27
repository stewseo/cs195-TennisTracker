package TestFixtures;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;


public class TestRowCell {

    @Mock
    Iterator<String> i;

    Comparable<String> c;

    int userId = 100;

    public TestRowCell() {}

    @Test
    void testMoreThanOneReturnValue() {
        when(i.next()).thenReturn("Full").thenReturn("Name");
        String result = i.next() + " " + i.next();
        assertEquals("Full Name", result);
    }


    @Test
    void testReturnValueDependentOnMethodParameter(@Mock Comparable<String> c) {
        when(c.compareTo("ID")).thenReturn(1);
        when(c.compareTo("player_id")).thenReturn(2);
        assertEquals(1, c.compareTo("Burrito"));
        assertEquals(2, c.compareTo("Pizza"));
    }

    @Test
    void testReturnValueInDependentOnMethodParameter2(@Mock Comparable<Integer> c )  {
        when(c.compareTo(isA(Integer.class))).thenReturn(0);
        assertEquals(0, c.compareTo(4));
    }


}
