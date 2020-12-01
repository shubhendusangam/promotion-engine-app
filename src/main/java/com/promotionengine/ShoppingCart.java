package com.promotionengine;

public class ShoppingCart {
   private int quantity;
   private SkuInformation product;
   private double totalPrice;

   public ShoppingCart(int quantity, SkuInformation product) {
      this.quantity = quantity;
      this.product = product;
   }

   public int getQuantity() {
      return quantity;
   }

   public SkuInformation getProduct() {
      return product;
   }

   public double getTotalPrice() {
      return totalPrice;
   }
}
