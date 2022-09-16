package se.iths.javatwentytwo.labOne;

import java.util.*;

public class LabOne {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PriceAndTime priceAndTime = new PriceAndTime();

        menu(priceAndTime, scan);
    }

    private static void printMenu(){
        System.out.println("Elpriser");
        System.out.println("========");
        System.out.println("1. Inmatning");
        System.out.println("2. Min, Max och Medel");
        System.out.println("3. Sortera");
        System.out.println("4. Bästa Laddningtid (4h)");
        System.out.println("e. Avsluta");
    }

    private static void menu(PriceAndTime priceAndTime, Scanner scan){

        do{
            printMenu();
            String alternative = scan.nextLine();
            switch (alternative.toLowerCase()) {
                case "1" -> priceAndTime.enterPrices();
                case "2" -> priceAndTime.printMinMaxAverage();
                case "3" -> priceAndTime.printSorted();
                case "4" -> priceAndTime.printBestRecharging();
                case "e" -> System.exit(0);
                default -> System.out.println("Invalid option, choose again");
            }
        }while(true);
    }
}
