package com.example.womenstorefx;

public class Store {
    private static double capital = 20000;
    private static double totalCost = 0;
    private static double totalIncome = 0;


    public static double getCapital() {
        return capital;
    }

    public static double getTotalCost() {
        return totalCost;
    }

    public static double getTotalIncome() {
        return totalIncome;
    }

    public static void updateCapitalAfterSale(double saleIncome){
        capital += saleIncome;
    }

    public static void updateCapitalAndCostAfterPurchase(double purchaseCost){
        totalCost +=purchaseCost;
        capital-=purchaseCost;
    }

    public static void updateTotalIncome(double saleIncome){
        totalIncome += saleIncome;
    }

    public static void displayFinancialSellStatus() {
        System.out.println("Current Capital: $" + capital);
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Cost: $" + totalCost);
    }

    public static void displayFinancialPurchaseStatus() {
        System.out.println("Current Capital: $" + capital);
        System.out.println("Total Income: $" + totalIncome);
    }


}
