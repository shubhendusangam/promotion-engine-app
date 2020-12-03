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

import com.constant.Constants;
import static com.constant.Constants.ORDER_LIST;
import static com.constant.Constants.QUANTITY;

public class ShoppingCart {
   // quantity, product
   private long quantity;
   public SkuInformation product;

   public ShoppingCart(long qty, SkuInformation pdt) {
      this.quantity = qty;
      this.product = pdt;
   }

   public long getQuantity() {
      return quantity;
   }

   public SkuInformation getProduct() {
      return product;
   }

   @Override
   public String toString() {
      return quantity + "           " + product.getSkuId();
   }

   public static List<ShoppingCart> getCartInfo(Map<String, SkuInformation> skuInformation) {
      List<ShoppingCart> shoppingCartList = new ArrayList<>();
      JSONParser jsonParser = new JSONParser();
      try {
         Object obj = jsonParser.parse(new FileReader("src/main/resources/orderlist.json"));
         JSONObject jsonObject = (JSONObject) obj;
         JSONArray orderLists = (JSONArray) jsonObject.get(ORDER_LIST);
         for (Object orderInfo : orderLists) {
            SkuInformation productInfo = new SkuInformation();
            JSONObject order = (JSONObject) orderInfo;
            String skuId = (String) order.get(Constants.SKU_ID);
            long quantity = (Long) order.get(QUANTITY);
            if (skuInformation.containsKey(skuId)){
               productInfo = skuInformation.get(skuId);
            }
            ShoppingCart shoppingCart = new ShoppingCart(quantity, productInfo);
            shoppingCartList.add(shoppingCart);
         }
      } catch (ParseException | IOException e) {
         e.getMessage();
      }
      return shoppingCartList;
   }
}
