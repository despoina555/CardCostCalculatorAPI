package despina.cardcost.utils;

import java.net.URI;
import org.springframework.web.reactive.function.client.WebClient;

public class BinlistProvider {


   public static String requestCardData(String cardNumber) {

       String result = WebClient.create().get()
                   .uri(URI.create("https://lookup.binlist.net/" + cardNumber))
                   .retrieve()
                   .bodyToMono(String.class)
                   .block();
       return result;
   }

}
