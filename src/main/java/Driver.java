import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.promotionengine.DiscountCalculator;
import com.promotionengine.PromotionOffer;
import com.promotionengine.ShoppingCart;
import com.promotionengine.SkuInformation;

import static java.lang.System.out;

public class Driver {
   public static void main(String[] args) {
      Map<String, SkuInformation> skuInformationMap = new HashMap<>();
      List<PromotionOffer> promotionOfferList;
      List<ShoppingCart> shoppingCartList;
      out.println("Welcome to promotional engine\n-----------------------------");

      out.println("Products Available in Platform\n-----------------------------");
      //sku information
      out.println("SKUid        Description           price");
      skuInformationMap = SkuInformation.readSkuInfo(skuInformationMap);
      SkuInformation.readSkuInfo(skuInformationMap).values().forEach(out::println);
      out.println("----------------------------------------------");

      out.println("Promotion Offer Available in Platform\n----------------------------------");
      // call promotion offer
      promotionOfferList = PromotionOffer.offersAvailable();
      promotionOfferList.forEach(out::println);
      out.println("---------------------------------------------");

      out.println("Cart Info \n--------------------");
      // call shopping cart
      out.println("Quantity     Skuid");
      shoppingCartList = ShoppingCart.getCartInfo(skuInformationMap);
      shoppingCartList.forEach(out::println);
      out.println("--------------------");

      // call shopping cart
      out.print("Total Price ");
      DiscountCalculator calculator = new DiscountCalculator(promotionOfferList, shoppingCartList);
      int totalPrice = calculator.calculateCartPrice();
      out.println(totalPrice);
      out.println("--------------------");
   }
}
