package CpuSchedulingFX;

import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.util.*;

import javafx.fxml.FXML;

import static java.util.Collections.swap;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}

class FirstComeFirstServe {
    protected void findWaitingTime(int[] processes, int size, int[] burstTime, int[] waitingTime) {
        waitingTime[0] = 0;
        for (int index = 1; index < size; index++)
            waitingTime[index] = burstTime[index - 1] + waitingTime[index - 1];
    }

    protected void findTurnAroundTime(int[] processes, int size, int[] burstTime, int[] waitingTime, int[] turnAroundTime) {
        for (int index = 0; index < size; index++) {
            turnAroundTime[index] = burstTime[index] + waitingTime[index];
        }
    }

    protected void findAverageTime(int[] process, int size, int[] burstTime) {
        int[] waitingTime = new int[size];
        int[] turnAroundTime = new int[size];
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        findWaitingTime(process, size, burstTime, waitingTime);
        findTurnAroundTime(process, size, burstTime, waitingTime, turnAroundTime);

        // output code for displaying processes along with all details

        for (int index = 0; index < size; index++) {
            totalWaitingTime += waitingTime[index];
            totalTurnAroundTime += turnAroundTime[index];

            // output code for printing totalTurnAroundTime, totalBurstTime and totalWaitingTime
        }
    }
}

class ShortestJobFirst {
    class NonPreemptive {
        void arrangeArrival(int size, List<ArrayList<Integer>> matrix) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (matrix.get(row).get(col) > matrix.get(row + 1).get(col)) {
                        for (int index = 0; index < size - 1; index++) {
                            swap(matrix.get(col).get(index), matrix.get(col + 1).get(index));
                        }
                    }
                }
            }
        }

        void completionTime(int size, List<ArrayList<Integer>> matrix) {
            int temp, value = 0;
            matrix.get(0).add(3, matrix.get(0).get(1) + matrix.get(0).get(2));
            matrix.get(0).add(5, matrix.get(0).get(3) + matrix.get(0).get(1));
            matrix.get(0).add(4, matrix.get(0).get(5) + matrix.get(0).get(2));

            for (int row = 1; row < size; row++) {
                temp = matrix.get(row - 1).get(3);
                int low = matrix.get(row).get(2);
                for (int col = row; col < size; col++) {
                    if (temp >= matrix.get(col).get(1) && low >= matrix.get(col).get(2)) {
                        low = matrix.get(col).get(2);
                        value = col;
                    }
                }
                matrix.get(value).add(3, temp + matrix.get(value).get(2));
                matrix.get(value).add(5, matrix.get(value).get(3) - matrix.get(value).get(1));
                matrix.get(value).add(4, matrix.get(value).get(5) - matrix.get(value).get(2));
                for (int index = 0; index < 6; index++) {
                    swap(matrix.get(value).get(index), matrix.get(row).get(index));
                }

            }
        }
    }

    // Shortest Remaining Time First
    class Preemptive {
    }


    private void swap(Integer integer, Integer integer1) {
        Integer temp = integer;
        integer = integer1;
        integer1 = temp;
    }
}
