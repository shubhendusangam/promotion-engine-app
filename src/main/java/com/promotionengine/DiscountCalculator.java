package com.promotionengine;

import java.util.List;

public class DiscountCalculator {
   // promotional offer, order list, final price

   private List<PromotionOffer> promotionOffers;
   private List<ShoppingCart> shoppingCarts;
   int finalPrice = 0;

   public DiscountCalculator(){}

   public DiscountCalculator(List<PromotionOffer> promotionOffers, List<ShoppingCart> shoppingCarts) {
      super();
      this.promotionOffers = promotionOffers;
      this.shoppingCarts = shoppingCarts;
   }

   public List<PromotionOffer> getPromotionOffers() {
      return promotionOffers;
   }

   public List<ShoppingCart> getShoppingCarts() {
      return shoppingCarts;
   }

   public int calculateCartPrice(){
      int individualProductPrice = 0;
      for (ShoppingCart shoppingCart : shoppingCarts) {
         long orderedQty = shoppingCart.getQuantity();
         String orderedProduct = shoppingCart.getProduct().getSkuId();
         for (int i = 0; i < promotionOffers.size(); i++) {
            if (promotionOffers.get(i).getSkuIds().contains(orderedProduct)) {
               PromotionOffer offer = promotionOffers.get(i);
               List<String> offeredProducts = offer.getSkuIds();
               long offeredQty = offer.getQuantity();
               individualProductPrice = calculateIndividualProductPrice(offeredProducts, orderedProduct, offeredQty, orderedQty, shoppingCart.getProduct(), offer);
               break;
            }
         }
         if (individualProductPrice == 0){
            individualProductPrice = (int)orderedQty * Integer.valueOf(shoppingCart.getProduct().getSkuPrice());
         }
         finalPrice += individualProductPrice;
      }
      return finalPrice;
   }

   private int calculateIndividualProductPrice(List<String> offeredProducts, String orderedProduct, long offeredQty, long orderedQty, SkuInformation productInfo, PromotionOffer offer) {
      int tempPrice = 0;
      while (orderedQty >= offeredQty && offeredProducts.size() == 1) {
         tempPrice += offer.getFinalPrice();
         orderedQty -= offeredQty;
      }
      return Math.toIntExact(tempPrice + (orderedQty * Integer.valueOf(productInfo.getSkuPrice())));
   }
}
