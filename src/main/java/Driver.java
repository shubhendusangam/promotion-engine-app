import com.promotionengine.SkuInformation;

public class Driver {
   public static void main(String[] args) {
      System.out.println("Welcome to promotional engine\n-----------------------------");
      System.out.println("Products Available in Platform\n-----------------------------");
      SkuInformation.readSkuInfo().forEach(System.out::println);
      System.out.println("--------------------------------------------------------------");
   }

}
