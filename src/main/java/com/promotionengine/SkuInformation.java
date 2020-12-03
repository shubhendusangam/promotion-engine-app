package com.promotionengine;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.constant.Constants.SKU_DESC;
import static com.constant.Constants.SKU_ID;
import static com.constant.Constants.SKU_LIST;
import static com.constant.Constants.SKU_PRICE;

public class SkuInformation {
   // skuid, skuDescription, skuprice
   private String skuId;
   private String skuDescription;
   private String skuPrice;

   public SkuInformation() {}

   public SkuInformation(String skuId, String skuDescription, String skuPrice) {
      super();
      this.skuId = skuId;
      this.skuDescription = skuDescription;
      this.skuPrice = skuPrice;
   }

   public String getSkuId() {
      return skuId;
   }

   public String getSkuDescription() {
      return skuDescription;
   }

   public String getSkuPrice() {
      return skuPrice;
   }

   @Override
   public String toString() {
      return skuId + "      " + skuDescription + "        " + skuPrice + "\n";
   }

   public static Map<String, SkuInformation> readSkuInfo(Map<String, SkuInformation> skuInformationMap){
      JSONParser jsonParser = new JSONParser();
      try {
         Object obj = jsonParser.parse(new FileReader("src/main/resources/skulist.json"));
         JSONObject jsonObject = (JSONObject) obj;
         JSONArray skuArray = (JSONArray) jsonObject.get(SKU_LIST);
         for (Object skuInfo : skuArray) {
            JSONObject sku = (JSONObject) skuInfo;
            String skuId = (String) sku.get(SKU_ID);
            String skuDescription = (String) sku.get(SKU_DESC);
            String skuPrice = (String) sku.get(SKU_PRICE);
            SkuInformation skuInformation = new SkuInformation(skuId, skuDescription, skuPrice);
            skuInformationMap.put(skuId, skuInformation);
         }
      } catch (ParseException | IOException e) {
         e.getMessage();
      }
      return skuInformationMap;
   }
}
