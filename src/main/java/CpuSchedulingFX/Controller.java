package CpuSchedulingFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

public class Controller implements Initializable {
    @FXML
    ComboBox<String> scheduleMethod;
    @FXML
    CheckBox preemptive;
    @FXML
    TextField pid, arrivalTime, burstTime, priority, timeQuantum;
    @FXML
    TableView<Process> table;
    @FXML
    TableColumn<Process, String> pidCol;
    @FXML
    TableColumn<Process, Double> arrivalTimeCol;
    @FXML
    TableColumn<Process, Double> burstTimeCol;
    @FXML
    TableColumn<Process, Double> waitingTimeCol;
    @FXML
    TableColumn<Process, Double> departureTimeCol;
    @FXML
    TableColumn<Process, Integer> priorityCol;
    @FXML
    TableColumn<Process, Double> startingTimeCol;

    @FXML
    HBox ganttChart;

    ObservableList<Process> processes = FXCollections.observableArrayList();
    public static boolean first;
    public static double maxWidth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first = true;
        scheduleMethod.getItems().addAll("FCFS", "SJF", "Priority", "Round Robin");
        scheduleMethod.getSelectionModel().selectFirst();

        preemptive.setVisible(false);
        priority.setVisible(false);
        timeQuantum.setVisible(false);

        // visible or hidden based on method
        scheduleMethod.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
            if (n.equals("FCFS")) {
                preemptive.setVisible(false);
                priority.setVisible(false);
                timeQuantum.setVisible(false);
            }
            else if (n.equals("SJF")) {
                preemptive.setVisible(true);
                priority.setVisible(false);
                timeQuantum.setVisible(false);
            }
            else if (n.equals("Priority")) {
                preemptive.setVisible(true);
                priority.setVisible(true);
                priority.setMaxWidth(priority.getMinWidth());
                timeQuantum.setVisible(false);
            }
            else if (n.equals("Round Robin")) {
                preemptive.setVisible(false);
                priority.setVisible(false);
                priority.setMaxWidth(0);
                timeQuantum.setVisible(true);
            }
        });

        pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        burstTimeCol.setCellValueFactory(new PropertyValueFactory<>("burstTime"));
        waitingTimeCol.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));
        departureTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        startingTimeCol.setCellValueFactory(new PropertyValueFactory<>("startingTime"));
        table.setEditable(true);
        table.setItems(processes);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
