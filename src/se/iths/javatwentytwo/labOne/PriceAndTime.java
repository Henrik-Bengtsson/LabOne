package se.iths.javatwentytwo.labOne;

import java.util.Arrays;
import java.util.Scanner;

public class PriceAndTime {
    Scanner scan = new Scanner(System.in);

    private final String[] hours = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08"};
    private final int[] pricePerHour = new int[8];

    public PriceAndTime(){
    }

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
        System.out.println("Lägsta priset är: " + minPrice(getPricePerHour()) + " öre, kl " + minTimeStart(minPrice(getPricePerHour()), getPricePerHour()) +
                "-" + minTimeStop(minTimeStart(minPrice(getPricePerHour()), getPricePerHour())));
        System.out.println("Högsta priset är: " + maxPrice(getPricePerHour()) + " öre, kl " + maxTimeStart(maxPrice(getPricePerHour()), getPricePerHour()) +
                "-" + maxTimeStop(maxTimeStart(maxPrice(getPricePerHour()), getPricePerHour())));
        System.out.println("Dygnets medelpris är: " + averagePrice(getPricePerHour()) + " öre");
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
    private String minTimeStart(int min, int[] minArray){
        String minTimeStart = getHours()[0];
        for (int i = 0; i < minArray.length; i++) {
            if(min == minArray[i])
                minTimeStart = getHours()[i];
        }
        return minTimeStart;
    }
    private String minTimeStop(String minTimeStart){
        String minTimeStop = getHours()[0];
        for (int i = 0; i < getHours().length; i++) {
            if (minTimeStart.equals(getHours()[i]))
                minTimeStop = getHours()[i+1];
        }
        return minTimeStop;
    }

    private int maxPrice(int[] maxPriceArray){
        int max = maxPriceArray[0];
        for (int j : maxPriceArray) {
            if (j > max)
                max = j;
        }
        return max;
    }
    private String maxTimeStart(int min, int[] maxArray){
        String maxTimeStart = getHours()[0];
        for (int i = 0; i < maxArray.length; i++) {
            if(min == maxArray[i])
                maxTimeStart = getHours()[i];
        }
        return maxTimeStart;
    }
    private String maxTimeStop(String maxTimeStart){
        String maxTimeStop = getHours()[0];
        for (int i = 0; i < getHours().length; i++) {
            if (maxTimeStart.equals(getHours()[i]))
                maxTimeStop = getHours()[i+1];
        }
        return maxTimeStop;
    }


    private double averagePrice(int[] averageArray){
        double sum = sumPrices(averageArray);
        return sum / averageArray.length;
    }
    private int sumPrices(int[] priceArray){
        int sum = 0;
        for (int j : priceArray) sum = j + sum;
        return sum;
    }

    ////////////////////// Sorting //////////////////////////
    public void printSorted(){
        int[] copyPrice = Arrays.copyOf(pricePerHour, pricePerHour.length);
        String[] copyTime = Arrays.copyOf(hours, hours.length);
        sortByPrice(copyPrice, copyTime);
        for (int i = 0; i < copyPrice.length; i++) {
            System.out.println(copyTime[i] + "-" + copyTime[i] + " -> " + copyPrice[i] + " öre");
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
        System.out.println("Medelpriset under dessa timmar är: " + averageBestPrice(bestRechargingPrice()));
    }

    private String bestRechargingTime(){
        return minTimeStart(minPrice(bestRechargingPrice()), bestRechargingPrice());
    }

    private int[] bestRechargingPrice(){
        int[] sum = new int[5];
        for (int i = 0; i < sum.length; i++) {
            int temp = 0;
            for (int j = i; j < i + 4; j++) {
                temp = pricePerHour[j] + temp;
            }
            sum[i] = temp;
        }
        return sum;
    }

    private double averageBestPrice(int[] bestRechargingPrice){
        double min = minPrice(bestRechargingPrice);
        return min / 4;
    }
}
