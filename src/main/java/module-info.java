module CpuSchedulingFX {
    requires javafx.controls;
    requires javafx.fxml;

    opens CpuSchedulingFX to javafx.fxml;
    exports CpuSchedulingFX;
}