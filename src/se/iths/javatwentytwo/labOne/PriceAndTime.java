package se.iths.javatwentytwo.labOne;

import java.util.Arrays;
import java.util.Scanner;

public class PriceAndTime {
    Scanner scan = new Scanner(System.in);

    private final String[] hours = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                                    "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    private final int[] pricePerHour = new int[24];

    public String[] getHours() {
        return hours;
    }

    public int[] getPricePerHour() {
        return pricePerHour;
    }

    ///////////////////// Enter value ///////////////////////
    public void enterPrices(){
        for (int i = 0; i < pricePerHour.length; i++) {
            System.out.print(hours[i] + "-" + hours[i + 1] + ": ");
            pricePerHour[i] = scan.nextInt();
        }
    }

    /////////////////// MinMaxAverage ///////////////////
    public void printMinMaxAverage(){
        System.out.println("Lägsta priset är: " + minPrice(getPricePerHour()) + " öre, kl " + timeStart(minPrice(getPricePerHour()), getPricePerHour()) +
                "-" + timeStop(timeStart(minPrice(getPricePerHour()), getPricePerHour())));
        System.out.println("Högsta priset är: " + maxPrice(getPricePerHour()) + " öre, kl " + timeStart(maxPrice(getPricePerHour()), getPricePerHour()) +
                "-" + timeStop(timeStart(maxPrice(getPricePerHour()), getPricePerHour())));
        System.out.println("Dygnets medelpris är: " + String.format("%.2f",averagePrice(sumPrices(getPricePerHour()), getPricePerHour().length)) + " öre");
    }

    private int minPrice(int[] minPriceArray){
        int min = minPriceArray[0];
        for (int j : minPriceArray) {
            if (j < min) {
                min = j;
            }
        }
        return min;
    }

    private int maxPrice(int[] maxPriceArray){
        int max = maxPriceArray[0];
        for (int j : maxPriceArray) {
            if (j > max)
                max = j;
        }
        return max;
    }

    private String timeStart(int min, int[] minArray){
        String timeStart = getHours()[0];
        for (int i = 0; i < minArray.length; i++) {
            if(min == minArray[i])
                timeStart = getHours()[i];
        }
        return timeStart;
    }

    private String timeStop(String timeStart){
        String timeStop = getHours()[0];
        for (int i = 0; i < getHours().length; i++) {
            if (timeStart.equals(getHours()[i]))
                timeStop = getHours()[i+1];
        }
        return timeStop;
    }

    private int sumPrices(int[] priceArray){
        int sum = 0;
        for (int j : priceArray) sum = j + sum;
        return sum;
    }
    private double averagePrice(int sum, int dividedBy){
        return (double) sum / dividedBy;
    }

    ////////////////////// Sorting //////////////////////////
    public void printSorted(){
        int[] copyPrice = Arrays.copyOf(pricePerHour, pricePerHour.length);
        String[] copyTime = Arrays.copyOf(hours, hours.length);
        sortByPrice(copyPrice, copyTime);
        for (int i = 0; i < copyPrice.length; i++) {
            System.out.println(copyTime[i] + "-" + timeStop(copyTime[i]) + " -> " + copyPrice[i] + " öre");
        }
    }

    private void sortByPrice(int[] copy, String[] copyTime){
        for (int i = 0; i < copyTime.length; i++) {
            for (int j = i + 1; j < copy.length; j++) {
                if(copy[i] > copy[j]){
                    int temp = copy[j];
                    String temp2 = copyTime[j];
                    copy[j] = copy[i];
                    copyTime[j] = copyTime[i];
                    copy[i] = temp;
                    copyTime[i] = temp2;
                }
            }
        }
    }

    /////////////////// BestRecharging //////////////////////
    public void printBestRecharging(){
        System.out.println("Den billigaste laddtiden under 4h är från kl: "  + bestRechargingTime() + ".00");
        System.out.println("Medelpriset under dessa timmar är: " + String.format("%.2f",averagePrice(minPrice(bestRechargingPrice()), 4)));
    }

    private String bestRechargingTime(){
        return timeStart(minPrice(bestRechargingPrice()), bestRechargingPrice());
    }

    private int[] bestRechargingPrice(){
        int[] sum = new int[21];
        for (int i = 0; i < sum.length; i++) {
            int temp = 0;
            for (int j = i; j < i + 4; j++) {
                temp = pricePerHour[j] + temp;
            }
            sum[i] = temp;
        }
        return sum;
    }

    /////////////////// Visualization /////////////////////
    public void printVisualization(){
        final int pointColumn = String.valueOf(maxPrice(getPricePerHour())).length();
        String[][] visualization = new String[15][getHours().length + pointColumn + 1];

        frame(visualization, pointColumn);
        table(visualization, pointColumn);

        for (String[] strings : visualization) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private void frame(String[][] visualization, int pointColumn){

        for (int row = 0; row < visualization.length; row++) {
            for (int column = 0; column < visualization[row].length; column++) {
                if (row == 0 && column == 0) {
                    visualization[row][column] = String.valueOf(maxPrice(getPricePerHour()));
                }else if (row == 12 && column == 0) {
                    visualization[row][column] = String.valueOf(minPrice(getPricePerHour()));
                } else if (column == pointColumn + 1) {
                    visualization[row][column] = "|";
                } else if (row == 13 && column > pointColumn + 1) {
                    visualization[row][column] = "---";
                } else if ((row == 14 && column > pointColumn)) {
                    int time = 0;
                    while(time < 24) {
                        visualization[row][column] = getHours()[time] + " ";
                        time++;
                        column++;
                    }
                } else
                    visualization[row][column] = " ";
            }
        }
    }

    private void table(String[][] visualization, int pointColumn){

    }
}
