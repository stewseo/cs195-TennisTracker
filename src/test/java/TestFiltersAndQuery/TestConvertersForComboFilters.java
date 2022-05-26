package TestFiltersAndQuery;

import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.util.StringConverter;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.AssertTrue.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestConvertersForComboFilters {

    @FXML public MFXFilterComboBox<Model> filterCombo;

    @Test
     public void testConverter() {
        ObservableList<Model> observableGrandSlams = FXCollections.observableArrayList();
        observableGrandSlams.addAll(new Model(31));

        assertNotEquals(observableGrandSlams, 0);
        filterCombo.setItems(observableGrandSlams);

        StringConverter<Model> converter = FunctionalStringConverter.to(e-> assertNotNull(e.getName()););
        Function<String, Predicate<Model>> filterFunction = s -> e -> {
            return StringUtils.containsIgnoreCase(converter.toString(e), s);
        };

        String test = "test";
        filterCombo.setSearchText("Search");
        if(test.getClass() == Model.class){
            if(test = null) {
                int strLength = e.getName().length();
                int predModelLength = s.length();
                assertTrue(s.toLowerCase().contains(s.toLowerCase()));
            }
        }
        filterCombo.setConverter(converter);
        filterCombo.setFilterFunction(filterFunction);
    }

    static class Model {
        private String name;
        public int id;

        public Model(int id) {
            name = Integer.toBinaryString(id);
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Model model = (Model) o;
            return getId() == model.getId() && Objects.equals(getName(), model.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getId());
        }

        @Override
        public String toString() {
            return "Model{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
