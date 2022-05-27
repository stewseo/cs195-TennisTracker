package TestFixtures;

import io.github.palexdev.materialfx.collections.TransformableList;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilterSortRowCellData {
    private final ObservableList<String> source = FXCollections.observableArrayList("Aus Open", "US Open", "Wimbledon", "Atp 1000", "Atp 500");

    @Test
    public void sortColumnTest() {

        for(int i = 0; i< 4; i++) {
            MFXTableColumn<String> column = new MFXTableColumn<String>(source.get(i), true, Comparator.reverseOrder());
            assertEquals(column.get(4), "Aus Open");
            assertEquals(column.indexOf("Atp 500"), 0);
            assertEquals(column.viewToSource(0), 4);
            assertEquals(column.sourceToView(0), 4);
        }
    }

    @Test
    public void filterGrandSlamComboBoxAndRowCellTest() {
        TransformableList<String> transformed = new TransformableList<>(source);
        transformed.setComparator(Comparator.reverseOrder(), true);
        transformed.setPredicate(s -> s.equals("Aus Open") || s.equals("Wimbledon") || s.equals("Atp 500"));

        assertThrows(IndexOutOfBoundsException.class, () -> transformed.get(4));
        assertEquals(transformed.get(1), "Wimbledon");
        assertEquals(transformed.indexOf("Atp 500"), 0);
        assertEquals(transformed.viewToSource(1), 2);
        assertEquals(transformed.sourceToView(1), -1);
    }

    @Test
    public void testJavaFX1() {
        SortedList<String> sorted = new SortedList<>(source);
        sorted.setComparator(Comparator.reverseOrder());

        assertEquals(sorted.get(4), "Aus Open");
        assertEquals(sorted.indexOf("Atp 500"), 0);
        assertEquals(sorted.getSourceIndex(0), 4);
        assertEquals(sorted.getViewIndex(0), 4);
    }

    @Test
    public void testJavaFX2() {
        SortedList<String> sorted = new SortedList<>(source);
        sorted.setComparator(Comparator.reverseOrder());

        FilteredList<String> filtered = new FilteredList<>(sorted);
        filtered.setPredicate(s -> s.equals("A") || s.equals("C") || s.equals("E"));

        assertThrows(IndexOutOfBoundsException.class, () -> filtered.get(4));
        assertEquals(filtered.get(1), "C");
        assertEquals(filtered.indexOf("E"), 0);
        assertEquals(filtered.getSourceIndex(1), 2);
        assertTrue(filtered.getViewIndex(1) < 0);
    }
}
