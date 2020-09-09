package despina.cardcost.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import despina.cardcost.exception.InvalidDataException;
import despina.cardcost.model.entity.Card;
import despina.cardcost.model.entity.CardDetailsPayload;
import despina.cardcost.repository.CardRepository;
import despina.cardcost.utils.BinlistProvider;
import org.openapitools.client.model.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

@Service
public class CardCostService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ObjectMapper mapper;

    @Transactional
    public CardDto calculateCardCost(String cardNumber) throws InvalidDataException {

        Card card = new Card();
        card.setCardNumber(cardNumber);

        if (cardRepository.findByCardNumber(cardNumber).isPresent()) {
            card= cardRepository.findByCardNumber(cardNumber).get();
        }else{
            try {
                CardDetailsPayload obj = mapper.readValue(BinlistProvider.requestCardData(cardNumber), CardDetailsPayload.class);
                performCostCalculation(obj, card);
                cardRepository.save(card);
            } catch (Exception e) {
                throw new InvalidDataException();
            }
        }

        CardDto cardDTO = new CardDto();
        cardDTO.setCost(card.getCost());
        cardDTO.setCountry(card.getCountry());
        return cardDTO;


    }

    private void performCostCalculation(CardDetailsPayload obj, Card card) {
        String country= obj.getCountry().getAlpha2();
        card.setCountry(obj.getCountry().getAlpha2());
        card.setCost(
                BigDecimal.valueOf(country.equalsIgnoreCase("US")
                        ? 5 :(country.equalsIgnoreCase("GR")? 15 :10)));

    }

}
