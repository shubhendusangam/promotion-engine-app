import com.promotionengine.PromotionOffer;
import com.promotionengine.SkuInformation;

import static java.lang.System.out;

public class Driver {
   public static void main(String[] args) {
      out.println("Welcome to promotional engine\n-----------------------------");

      out.println("Products Available in Platform\n-----------------------------");
      //sku information
      out.println("SKUid        Description           price");
      SkuInformation.readSkuInfo().forEach(out::print);
      out.println("----------------------------------------------");

      out.println("Promotion Offer Available in Platform\n----------------------------------");
      // call promotion offer
      PromotionOffer.offersAvailable().forEach(out::println);
      out.println("---------------------------------------------");
   }
}
