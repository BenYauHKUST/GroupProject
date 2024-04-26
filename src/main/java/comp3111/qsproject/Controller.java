package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Controller {
    @FXML
    public ToggleButton t0MusicButton;
    MediaPlayer mediaPlayer;
    /* T1 Controller */
    public TableView<QSItem> t1DataTable;
    @FXML
    public ChoiceBox<String> t1YearChoiceBox;

    @FXML
    public BarChart<String, Double> t1BarChart;

    @FXML
    public TableColumn<QSItem, String> t1Rank;

    @FXML
    public TableColumn<QSItem, String> t1University;

    @FXML
    public TableColumn<QSItem, String> t1Score;

    @FXML
    public TableColumn<QSItem, String> t1Country;

    @FXML
    public TableColumn<QSItem, String> t1City;

    @FXML
    public TableColumn<QSItem, String> t1Type;

    @FXML
    public PieChart t1PieChart;

    @FXML
    public ChoiceBox<String> t1PieChartChoiceBox;

    @FXML
    public Label t1PieChartLabel;

    @FXML
    public ChoiceBox<String> t1BarChartChoiceBox;

    @FXML
    public Label t1BarChartLabel;

    @FXML
    public CategoryAxis t1BarChartTypeXaxis;

    /* T2 Controller */
    @FXML
    public ChoiceBox<String> t2University1ChoiceBox;
    @FXML
    public ChoiceBox<String> t2University2ChoiceBox;
    @FXML
    public ChoiceBox<String> t2CountryRegion1ChoiceBox;
    @FXML
    public ChoiceBox<String> t2CountryRegion2ChoiceBox;

    @FXML
    public CheckBox t22017CheckBox;
    @FXML
    public CheckBox t22018CheckBox;
    @FXML
    public CheckBox t22019CheckBox;
    @FXML
    public CheckBox t22020CheckBox;
    @FXML
    public CheckBox t22021CheckBox;
    @FXML
    public CheckBox t22022CheckBox;
    @FXML
    public CheckBox t22017CheckBox2;
    @FXML
    public CheckBox t22018CheckBox2;
    @FXML
    public CheckBox t22019CheckBox2;
    @FXML
    public CheckBox t22020CheckBox2;
    @FXML
    public CheckBox t22021CheckBox2;
    @FXML
    public CheckBox t22022CheckBox2;

    @FXML
    public BarChart<Double, String> t21RankBarChart;
    @FXML
    public BarChart<Double, String> t21ScoreBarChart;
    @FXML
    public BarChart<Double, String> t21FacultyBarChart;
    @FXML
    public BarChart<Double, String> t21InternationalBarChart;
    @FXML
    public BarChart<Double, String> t21SFRBarChart;
    @FXML
    public LineChart<String, Double> t21LineChart;

    @FXML
    public BarChart<Double, String> t22RankBarChart;
    @FXML
    public BarChart<Double, String> t22ScoreBarChart;
    @FXML
    public BarChart<Double, String> t22FacultyBarChart;
    @FXML
    public BarChart<Double, String> t22InternationalBarChart;
    @FXML
    public BarChart<Double, String> t22SFRBarChart;
    @FXML
    public LineChart<String, Double> t22LineChart;

    /* T3 Controller */

    @FXML
    public TextField t3TopRankTextField;
    @FXML
    public TextField t3BottomRankTextField;
    @FXML
    public ComboBox<String> t3TypeComboBox;
    @FXML
    public ComboBox<String> t3RegionComboBox;
    @FXML
    public ComboBox<String> t3CountryComboBox;
    @FXML
    public Label t3RangeError;
    @FXML
    public Label t3ResultMessage;
    @FXML
    public Label t3CountryLabel;
    @FXML
    public TableView<RecommendItem> t3TableView;

    @FXML
    public TableColumn<RecommendItem, String> t3University;
    @FXML
    public TableColumn<RecommendItem, String> t3BestYear;

    @FXML
    public TableColumn<RecommendItem, String> t3BestRank;

    @FXML
    public TableColumn<RecommendItem, String> t3RecentYear;

    @FXML
    public TableColumn<RecommendItem, String> t3RecentRank;

    ObservableList<String> yearList = FXCollections.observableArrayList("2017", "2018", "2019", "2020", "2021", "2022");
    ObservableList<String> stringPropertyList = FXCollections.observableArrayList("country", "region", "size", "type", "researchOutput");
    ObservableList<String> typeList = QSList.type;
    ObservableList<String> regionList = QSList.region;
    ObservableList<String> universityList = QSList.university;
    ObservableList<String> country_regionList = QSList.country;
    HashMap<String, ObservableList<String>> countryRegionDict = QSList.countryRegion;

    @FXML
    private void initialize() {
        // Whole Program Information
        QSList.initialize();

        // Loop background music
        t0MusicButton.setSelected(false);
        String path = getClass().getResource("UniversityAnthem.mp3").getPath();
        Media source = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(source);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        t0MusicButton.setOnAction(this::handelMusic);

        // T1
        t1YearChoiceBox.setItems(yearList);
        t1YearChoiceBox.setValue("2017");
        t1PieChartChoiceBox.setItems(stringPropertyList);
        t1PieChartChoiceBox.setValue("size");
        t1PieChartLabel.setText("");
        t1BarChartChoiceBox.setItems(stringPropertyList);
        t1BarChartChoiceBox.setValue("type");
        t1BarChartLabel.setText("");

        t1Rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        t1University.setCellValueFactory(new PropertyValueFactory<>("name"));
        t1Score.setCellValueFactory(new PropertyValueFactory<>("score"));
        t1Country.setCellValueFactory(new PropertyValueFactory<>("country"));
        t1City.setCellValueFactory(new PropertyValueFactory<>("city"));
        t1Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        // T2
        universityList.sort(Comparator.comparing(item -> item));
        t2University1ChoiceBox.setItems(universityList);
        t2University1ChoiceBox.setValue("");
        t2University2ChoiceBox.setItems(universityList);
        t2University2ChoiceBox.setValue("");
        country_regionList.addAll(regionList);
        country_regionList.sort(Comparator.comparing(item -> item));
        country_regionList.add("All");
        t2CountryRegion1ChoiceBox.setItems(country_regionList);
        t2CountryRegion1ChoiceBox.setValue("All");
        t2CountryRegion2ChoiceBox.setItems(country_regionList);
        t2CountryRegion1ChoiceBox.setValue("All");
        /*
            Your Code Here.
            1. Initialize the Choice boxes of university.
            2. Initialize the Choice boxes of country/region.
            3. For choice boxes of country/region,
                you need to add a blank or "All" option representing selection of all the country/region.
         */
        // T3
        typeList.add("All"); typeList.remove("");
        t3TypeComboBox.setItems(typeList);
        t3TypeComboBox.setValue("All");

        regionList.add("All");
        t3RegionComboBox.setItems(regionList);
        t3RegionComboBox.setValue("All");
        t3RangeError.setText("");

        for (String key : countryRegionDict.keySet())
            countryRegionDict.get(key).add("All");
        t3RegionComboBox.setOnAction(this::t3handleRegionSelection);

        t3ResultMessage.setText("");
    }

    @FXML
    private void T1_onClickClear() {

        /*
            Your Code Here.
            Reset the Page Task1. (including the choice box, labels and charts)
         */

        t1YearChoiceBox.setValue("2017");
        t1PieChartChoiceBox.setValue("size");
        t1BarChartChoiceBox.setValue("type");

        // Clear labels
        t1PieChartLabel.setText("");
        t1BarChartLabel.setText("");

        // Clear table data
        t1DataTable.getItems().clear();

        // Clear pie chart data
        t1PieChart.getData().clear();

        // Clear bar chart data
        t1BarChart.getData().clear();
    }

    @FXML
    private void T1_onClickSearch() {
        /*
            Your Code Here.
            When click search on Task1:
                1. Fetch the year from the choice box.
                2. Clear previous data.
                3. Make an Analyser.
                4. Update the Table view, which shows Information about universities.
                5. Update the Pie Chart, which shows the sum score of selected property (t1PieChartChoiceBox).
                6. Update the Bar Chart, which shows the average score of selected property (t1BarChartChoiceBox).
            Please notice that we need listeners for monitoring the changes of choice box in pie chart and bar chart.
         */

        //Table
        String yearToSearch = t1YearChoiceBox.getValue();
        T1Analysis analyser = new T1Analysis(yearToSearch);
        t1DataTable.setItems(analyser.getTableList());

        //PieChart
        String pieChartSearchName = t1PieChartChoiceBox.getValue();
        ObservableList<PieChart.Data> pieChartData = analyser.getPieChartData(pieChartSearchName);
        t1PieChart.setData(pieChartData);

        //BarChart
        t1BarChart.getData().clear();
        String barChartSearchName = t1BarChartChoiceBox.getValue();
        XYChart.Series<String, Double> barChartData = analyser.getBarChartData(barChartSearchName);
        t1BarChart.getData().add(barChartData);
    }

    @FXML
    private void T21_onClickClear() {
        /*
            Your Code Here.
            Reset the Page Task 2.1. (including the choice boxes, check boxes and charts)
         */
        //Clear Choice Boxes
        t2University1ChoiceBox.setValue("");
        t2University2ChoiceBox.setValue("");

        //Clear Check Boxes
        t22017CheckBox.setSelected(false);
        t22018CheckBox.setSelected(false);
        t22019CheckBox.setSelected(false);
        t22020CheckBox.setSelected(false);
        t22021CheckBox.setSelected(false);
        t22022CheckBox.setSelected(false);

        //Clear Charts
        t21FacultyBarChart.getData().clear();
        t21LineChart.getData().clear();
        t21InternationalBarChart.getData().clear();
        t21RankBarChart.getData().clear();
        t21ScoreBarChart.getData().clear();
        t21SFRBarChart.getData().clear();
    }

    @FXML
    private void T21_onClickCompare() {
        /*
            Your Code Here.
            When click search on Task2.1:
                1. Fetch the two universities from the choice box.
                2. Fetch the selected years.
                3. Clear previous data.
                4. Make an Analyser.
                5. Update the Bar Charts, which shows the average of selected property.
                6. Update the line Chart, which shows two lines of score of each year.
         */
        //Fetching two unis
        String university1 = t2University1ChoiceBox.getValue();
        String university2 = t2University2ChoiceBox.getValue();

        //Fetching selected years
        List<String> selectedYears = new ArrayList<>();
        if (t22017CheckBox.isSelected()) selectedYears.add("2017");
        if (t22018CheckBox.isSelected()) selectedYears.add("2018");
        if (t22019CheckBox.isSelected()) selectedYears.add("2019");
        if (t22020CheckBox.isSelected()) selectedYears.add("2020");
        if (t22021CheckBox.isSelected()) selectedYears.add("2021");
        if (t22022CheckBox.isSelected()) selectedYears.add("2022");

        // Clear previous data
        t21RankBarChart.getData().clear();
        t21ScoreBarChart.getData().clear();
        t21FacultyBarChart.getData().clear();
        t21InternationalBarChart.getData().clear();
        t21SFRBarChart.getData().clear();
        t21LineChart.getData().clear();

        // Make an Analyser
        T21Analysis analyser = new T21Analysis(university1, university2, selectedYears);

        // Update the Bar Charts
        t21RankBarChart.getData().add(analyser.getBarChartData("rank"));
        t21ScoreBarChart.getData().add(analyser.getBarChartData("score"));
        t21FacultyBarChart.getData().add(analyser.getBarChartData("facultyCount"));
        t21InternationalBarChart.getData().add(analyser.getBarChartData("internationalStudents"));
        t21SFRBarChart.getData().add(analyser.getBarChartData("studentFacultyRatio"));

        // Update the line Chart
        t21LineChart.getData().addAll(analyser.getLineChartData("score"));

    }

    @FXML
    private void T22_onClickClear() {
        /*
            Your Code Here.
            Reset the Page Task 2.2. (including the choice boxes, check boxes and charts)
         */
        //Clear Choice Boxes
        t2CountryRegion1ChoiceBox.setValue("All");
        t2CountryRegion2ChoiceBox.setValue("All");

        //Clear Check Boxes
        t22017CheckBox2.setSelected(false);
        t22018CheckBox2.setSelected(false);
        t22019CheckBox2.setSelected(false);
        t22020CheckBox2.setSelected(false);
        t22021CheckBox2.setSelected(false);
        t22022CheckBox2.setSelected(false);

        //Clear Charts
        t22FacultyBarChart.getData().clear();
        t22LineChart.getData().clear();
        t22InternationalBarChart.getData().clear();
        t22RankBarChart.getData().clear();
        t22ScoreBarChart.getData().clear();
        t22SFRBarChart.getData().clear();
    }

    @FXML
    private void T22_onClickCompare() {
        /*
            Your Code Here.
            When click search on Task2.2:
                1. Fetch the two country/region from the choice box.
                2. Fetch the selected years.
                3. Clear previous data.
                4. Make an Analyser.
                5. Update the Bar Charts, which shows the average of selected property.
                6. Update the line Chart, which shows two lines of score of each year.
         */
        //Fetching two country/region
        String country_region1 = t2CountryRegion1ChoiceBox.getValue();
        String country_region2 = t2CountryRegion2ChoiceBox.getValue();

        //Fetching selected years
        List<String> selectedYears = new ArrayList<>();
        if (t22017CheckBox2.isSelected()) selectedYears.add("2017");
        if (t22018CheckBox2.isSelected()) selectedYears.add("2018");
        if (t22019CheckBox2.isSelected()) selectedYears.add("2019");
        if (t22020CheckBox2.isSelected()) selectedYears.add("2020");
        if (t22021CheckBox2.isSelected()) selectedYears.add("2021");
        if (t22022CheckBox2.isSelected()) selectedYears.add("2022");

        // Clear previous data
        t22RankBarChart.getData().clear();
        t22ScoreBarChart.getData().clear();
        t22FacultyBarChart.getData().clear();
        t22InternationalBarChart.getData().clear();
        t22SFRBarChart.getData().clear();
        t22LineChart.getData().clear();

        // Make an Analyser
        T22Analysis analyser = new T22Analysis(country_region1, country_region2, selectedYears);

        // Update the Bar Charts
        t22RankBarChart.getData().add(analyser.getBarChartData("rank"));
        t22ScoreBarChart.getData().add(analyser.getBarChartData("score"));
        t22FacultyBarChart.getData().add(analyser.getBarChartData("facultyCount"));
        t22InternationalBarChart.getData().add(analyser.getBarChartData("internationalStudents"));
        t22SFRBarChart.getData().add(analyser.getBarChartData("studentFacultyRatio"));

        // Update the line Chart
        t22LineChart.getData().addAll(analyser.getLineChartData("score"));
    }

    @FXML
    private void T3_onClickClear() {
        t3TypeComboBox.setValue("All");
        t3RegionComboBox.setValue("All");
        t3TopRankTextField.setText("");
        t3RangeError.setText("");
        t3BottomRankTextField.setText("");
        t3TableView.getItems().clear();
        t3ResultMessage.setText("");
    }

    @FXML
    private void T3_onClickRecommend() {
        t3ResultMessage.setText("");
        String topValue = t3TopRankTextField.getText();
        String bottomValue = t3BottomRankTextField.getText();
        if (topValue.isEmpty() || bottomValue.isEmpty()) {
            t3RangeError.setText("Missing Top and/or Bottom values.");
            return;
        }
        try {
            int x = Integer.parseInt(topValue);
            int y = Integer.parseInt(bottomValue);
            if (x > y || x <= 0) {
                t3RangeError.setText("Invalid input of Top and/or Bottom values.");
                return;
            }
        }
        catch (Exception e) {
            t3RangeError.setText("Input for Top and/or Bottom values should be integer.");
            return;
        }
        t3RangeError.setText("");
        String type = t3TypeComboBox.getValue();
        String region = t3RegionComboBox.getValue();
        String country = t3CountryComboBox.getValue();
        t3TableView.getItems().clear();
        T3Analysis object = new T3Analysis(topValue, bottomValue, type, region, country);
        t3University.setCellValueFactory(new PropertyValueFactory<>("name"));
        t3BestYear.setCellValueFactory(new PropertyValueFactory<>("bestYear"));
        t3BestRank.setCellValueFactory(new PropertyValueFactory<>("bestRank"));
        t3RecentYear.setCellValueFactory(new PropertyValueFactory<>("recentYear"));
        t3RecentRank.setCellValueFactory(new PropertyValueFactory<>("recentRank"));
        t3TableView.getColumns().setAll(t3University, t3BestYear, t3BestRank, t3RecentYear, t3RecentRank);
        ObservableList<RecommendItem> temp = object.getRecommendData();
        if (temp.isEmpty()) {
            t3ResultMessage.setText("No university in recommendation list!");
        }
        else {
            t3ResultMessage.setText("Results have been successfully shown!");
        }
        t3TableView.setItems(temp);

    }

    private void t3handleRegionSelection(ActionEvent event) {
        String selectedRegion = t3RegionComboBox.getValue();
        if (selectedRegion.equals("All")) {
            t3CountryComboBox.setVisible(false);
            t3CountryLabel.setVisible(false);
        }
        else {
            t3CountryComboBox.setVisible(true);
            t3CountryLabel.setVisible(true);
            t3CountryComboBox.setItems(countryRegionDict.get(selectedRegion));
            t3CountryComboBox.setValue("All");
        }
    }

    private void handelMusic(ActionEvent event) {
        if (t0MusicButton.isSelected()) {
            mediaPlayer.play();
            t0MusicButton.setText("Music On!");
        }
        else {
            mediaPlayer.pause();
            t0MusicButton.setText("Music Off!");
        }
    }
}