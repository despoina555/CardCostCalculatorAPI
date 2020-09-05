package despina.cardcost.utils;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.server.ResponseStatusException;


public class BinlistProvider {


   public static String requestCardData(String cardNumber)  {

       try {
           String result = WebClient.create().get()
                   .uri(URI.create("https://lookup.binlist.net/" + cardNumber))
                   .retrieve()
                   .bodyToMono(String.class)
                   .block();

           return result;
       }catch( WebClientException exc){
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "Card Not Found", exc.getMostSpecificCause());
       }



    }

}
