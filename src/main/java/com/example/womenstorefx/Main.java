package com.example.womenstorefx;

import com.example.womenstorefx.Products.Accessories;
import com.example.womenstorefx.Products.Clothes;
import com.example.womenstorefx.Products.Product;
import com.example.womenstorefx.Products.Shoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*Clothes shirt= new Clothes("Shirt",31.25, 20,38);
        Clothes dress = new Clothes("Dress", 52.50, 15, 34);
        Shoes sneackers = new Shoes("Sneakers", 98.99,10, 39);
        Accessories cap= new Accessories("Cap", 18.35, 27);


        //Product[] productList = {shirt, dress, sneackers,cap};

        List<Product> productList = new ArrayList<>();
        productList.add(shirt);
        productList.add(dress);
        productList.add(sneackers);
        productList.add(cap);

        //Display intital stock with initial prices and before sorting
        System.out.println("\nInitial stock before sorting : ");
        for(Product product : productList){
            System.out.println(product.toString());
        }

        //Sorting the stock on products prices
        Collections.sort(productList);

        //Display intital stock after sorting
        System.out.println("\nInitial stock after sorting : ");
        for(Product product : productList){
            System.out.println(product.toString());
        }

        //Display productList with their sale prices
        System.out.println("\nPrices during sale : ");
        for (Product product : productList){
            product.applyDiscount();
            System.out.println(product.getName()+ " : "+ product.getPrice());
        }


        // Do some sale and puchase operations
        try {
            shirt.sell(5);
            dress.purchase(5,10);
            cap.sell(10);
            sneackers.sell(15);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        //Display stock after sales and purchases
        System.out.println("\nStock after sales and purchases :");
        for (Product product : productList){
            System.out.println(product.getName() + " : " + product.getNbItems() + " items\n");
        }



        Store.updateCapitalAfterSale(500); // Assuming a sale was made
        Store.updateCapitalAndCostAfterPurchase(200); // Assuming a purchase was made

        // Displaying the financial status
        Store.displayFinancialStatus();*/


        /*Clothes tShirt = new Clothes("T-Shirt", 20.0, 100, 42);

        // Apply a 10% discount
        tShirt.applyDiscount(10);
        System.out.println("Price after discount: " + tShirt.getPrice());

        // Stop the discount
        tShirt.stopDiscount();
        System.out.println("Price after stopping discount: " + tShirt.getPrice());*/




        /*try {
            Clothes.addProduct("T-Shirt", 19.99, 50, 38);
            Clothes.addProduct("Dress", 25.00, 20, 36);
            Clothes.deleteClothes(1);
            Clothes.modifyClothes(2, "Dresses", 25.00, 20, 36);
            Shoes.addShoe("Boots", 87.25, 3, 37);
            Shoes.addShoe("Sneakers", 65.25, 7, 38);
            Shoes.deleteShoe(2);
            Shoes.modifyShoe(1, "Boots", 65.25, 7, 35);
            Accessories.addAccessory("Earrings", 10.99, 15);
            Accessories.addAccessory("Necklace", 21.99, 10);
            Accessories.deleteAccessory(1);
            Accessories.addAccessory("Earrings", 10.99, 15);
            Accessories.modifyAccessory(2,"Necklace", 21.99, 20);
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {

            Shoes runningShoes = new Shoes( "Running Shoes", 120.0, 50, 38);

            runningShoes.sell(10);

            System.out.println("Product added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
