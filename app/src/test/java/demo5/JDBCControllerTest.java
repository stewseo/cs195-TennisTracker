package demo5;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Fields;
import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.testfx.api.FxRobot;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.MenuItemMatchers.hasText;


@ExtendWith(MockitoExtension.class)
public class JDBCControllerTest {

    @Mock
    Iterator<String> i;

    Comparable<String> c;

    int userId = 100;

    public JDBCControllerTest() {}

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
