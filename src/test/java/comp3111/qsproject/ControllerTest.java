package comp3111.qsproject;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    public FXMLLoader fxmlLoader;
    public Controller controller;
    ControllerTest() throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        controller = fxmlLoader.getController();
    }
    @BeforeAll
    public static void startJavaFXRuntime() {
        Platform.startup(() -> {});
    }
    @Test
    public void initializer() {
        // Check member variables here
        assertEquals("2017", controller.t1YearChoiceBox.getValue());
        assertEquals("size", controller.t1PieChartChoiceBox.getValue());
        assertEquals("All", controller.t2CountryRegion1ChoiceBox.getValue());
        assertEquals("All", controller.t3RegionComboBox.getValue());
    }
    @Test
    public void T21_onClickCompare() {
        controller.t2University1ChoiceBox.setValue("The Chinese University of Hong Kong (CUHK)");
        controller.t2University2ChoiceBox.setValue("The Hong Kong University of Science and Technology");
        controller.t22017CheckBox.setSelected(true);
        // Call the method
        controller.T21_onClickCompare();
        assertEquals(81.8, controller.t21LineChart.getData().getFirst().getData().getLast().getYValue());
    }
    @Test
    public void T22_onClickCompare() {
        // Init member variables here
        controller.t2CountryRegion1ChoiceBox.setValue("Hong Kong SAR");
        controller.t2CountryRegion2ChoiceBox.setValue("Japan");
        controller.t22017CheckBox2.setSelected(true);
        // Call the method
        controller.T22_onClickCompare();
        assertEquals(57, Math.round(controller.t22LineChart.getData().getFirst().getData().getLast().getYValue()));
    }
}
