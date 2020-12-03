import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.promotionengine.PromotionOffer;
import com.promotionengine.ShoppingCart;
import com.promotionengine.SkuInformation;

import static java.lang.System.out;

public class Driver {
   public static void main(String[] args) {
      Map<String, SkuInformation> skuInformationMap = new HashMap<>();
      out.println("Welcome to promotional engine\n-----------------------------");

      out.println("Products Available in Platform\n-----------------------------");
      //sku information
      out.println("SKUid        Description           price");
      skuInformationMap = SkuInformation.readSkuInfo(skuInformationMap);
      SkuInformation.readSkuInfo(skuInformationMap).values().forEach(out::println);
      out.println("----------------------------------------------");

      out.println("Promotion Offer Available in Platform\n----------------------------------");
      // call promotion offer
      PromotionOffer.offersAvailable().forEach(out::println);
      out.println("---------------------------------------------");

      out.println("Cart Info \n--------------------");
      // call shopping cart
      out.println("Quantity     Skuid");
      ShoppingCart.getCartInfo(skuInformationMap).forEach(out::println);
      out.println("--------------------");
   }
}
