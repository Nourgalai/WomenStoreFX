package com.example.womenstorefx;

public interface Discount {
    void applyDiscount(int productId, double discountPercentage, String tableName);

    void stopDiscount(int productId, String tableName);
}

