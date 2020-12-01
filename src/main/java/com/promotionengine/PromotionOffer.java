package com.promotionengine;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.constant.Constants;

import static com.constant.Constants.FINAL_PRICE;
import static com.constant.Constants.OFFER;
import static com.constant.Constants.QUANTITY;

public class PromotionOffer {
   // skuid, quantity, finalprice
   private List<String> skuIds;
   private long quantity;
   private long finalPrice;

   public PromotionOffer(List<String> skuIds, long quantity, long finalPrice) {
      this.skuIds = skuIds;
      this.quantity = quantity;
      this.finalPrice = finalPrice;
   }

   public List<String> getSkuIds() {
      return skuIds;
   }

   public long getQuantity() {
      return quantity;
   }

   public long getFinalPrice() {
      return finalPrice;
   }

   @Override
   public String toString() {
      return  "" + quantity + " of " + StringUtils.join(skuIds, " & ") + "'s for " + finalPrice;
   }

   public static List<PromotionOffer> offersAvailable() {
      List<PromotionOffer> promotionOfferList = new ArrayList<>();
      JSONParser jsonParser = new JSONParser();
      try {
         Object obj = jsonParser.parse(new FileReader("src/main/resources/activeoffer.json"));
         JSONObject jsonObject = (JSONObject) obj;
         JSONArray offerArray = (JSONArray) jsonObject.get(OFFER);
         for (Object offerInfo : offerArray) {
            JSONObject offer = (JSONObject) offerInfo;
            JSONArray skuIds = (JSONArray) offer.get(Constants.SKU_ID);
            List<String> skuIdList = new ArrayList<>();
            for (int i = 0; i < skuIds.size(); i++) {
               skuIdList.add(String.valueOf(skuIds.get(i)));
            }
            long quantity = (Long) offer.get(QUANTITY);
            long finalPrice = (Long) offer.get(FINAL_PRICE);
            PromotionOffer promotionOffer = new PromotionOffer(skuIdList, quantity, finalPrice);
            promotionOfferList.add(promotionOffer);
         }
      } catch (ParseException | IOException e) {
         e.getMessage();
      }
      return promotionOfferList;
   }
}

