package com.promotionengine;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SkuInformation {
   private String SKUID;
   private String skuDescription;
   private String skuPrice;

   public SkuInformation(String SKUID, String skuDescription, String skuPrice) {
      super();
      this.SKUID = SKUID;
      this.skuDescription = skuDescription;
      this.skuPrice = skuPrice;
   }

   public String getSKUID() {
      return SKUID;
   }

   public String getSkuDescription() {
      return skuDescription;
   }

   public String getSkuPrice() {
      return skuPrice;
   }

   @Override
   public String toString() {
      return "Sku ID " + SKUID + "\n"+
            "Sku Description " + skuDescription + "\n"+
            "Sku price " + skuPrice;
   }

   public static List<SkuInformation> readSkuInfo(){
      List<SkuInformation> skuInformationList = new ArrayList<>();
      JSONParser jsonParser = new JSONParser();
      try {
         Object obj = jsonParser.parse(new FileReader("src/main/resources/skulist.json"));
         JSONObject jsonObject = (JSONObject) obj;
         JSONArray skuArray = (JSONArray) jsonObject.get("skulist");
         for (Object skuInfo : skuArray) {
            JSONObject sku = (JSONObject) skuInfo;
            String skuId = (String) sku.get("SKUID");
            String skuDescription = (String) sku.get("skuDescription");
            String skuPrice = (String) sku.get("skuPrice");
            SkuInformation skuInformation = new SkuInformation(skuId, skuDescription, skuPrice);
            skuInformationList.add(skuInformation);
         }
      } catch (ParseException | IOException e) {
         e.getMessage();
      }
      return skuInformationList;
   }
}
