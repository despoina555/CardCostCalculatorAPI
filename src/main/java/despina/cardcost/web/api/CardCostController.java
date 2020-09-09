package despina.cardcost.web.api;

import despina.cardcost.service.CardCostService;
import lombok.SneakyThrows;
import org.openapitools.client.model.CardCostRequest;
import org.openapitools.client.model.CardCostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.mappers.ModelMapper;

@RestController
public class CardCostController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CardCostService cardCostService;


    @SneakyThrows
    @PostMapping("/payment-cards-cost")
    public ResponseEntity<CardCostResponse> handle(@RequestBody CardCostRequest cardCostRequest ){
        CardCostResponse cardCostResponse = new CardCostResponse();
        cardCostResponse.setCard(cardCostService.calculateCardCost(cardCostRequest.getCardNumber()));
         return ResponseEntity.ok(cardCostResponse) ;
    }

}
