package despina.cardcost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.CardCostRequest;
import org.openapitools.client.model.CardCostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CardcostApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    // Happy Paths
    @Test
    public void calculateCostOfGBCard() throws Exception {

        MockHttpServletResponse result = performPOSTRequest("49037360");
        assertEquals(200, result.getStatus());

        var responseAsObj = asObject(result.getContentAsString(), CardCostResponse.class);
        assertEquals("GB",responseAsObj.getCard().getCountry());
        assertEquals(new BigDecimal(10),responseAsObj.getCard().getCost());

    }


    @Test
    public void calculateCostOfUSCard() throws Exception {

        MockHttpServletResponse result = performPOSTRequest("372888");
        assertEquals(200, result.getStatus());

        var responseAsObj = asObject(result.getContentAsString(), CardCostResponse.class);
        assertEquals("US",responseAsObj.getCard().getCountry());
        assertEquals(new BigDecimal(5),responseAsObj.getCard().getCost());

    }

    @Test
    public void calculateCostOfGRCard() throws Exception {

        MockHttpServletResponse result = performPOSTRequest("510001");
        assertEquals(200, result.getStatus());

        var responseAsObj = asObject(result.getContentAsString(), CardCostResponse.class);
        assertEquals("GR",responseAsObj.getCard().getCountry());
        assertEquals(new BigDecimal(15),responseAsObj.getCard().getCost());

    }

    // Invalid Card Number
    @Test
    public void calculateCostOfInvalidCardNumber() throws Exception {

        MockHttpServletResponse result =    mockMvc.perform(MockMvcRequestBuilders.post("/payment-cards-cost")
                .content(createCardCostRequestAsJson("55667"))
                .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse();
                 assertEquals(400, result.getStatus());
    }



    private MockHttpServletResponse performPOSTRequest(String cardNumber) throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.post("/payment-cards-cost")
                .content(createCardCostRequestAsJson(cardNumber))
                .contentType("application/json"))
              //  .andExpect(status().isOk())
                .andReturn()
                .getResponse();

    }

    private String createCardCostRequestAsJson(String cardNumber){
        var command = new CardCostRequest();
        command.setCardNumber(cardNumber);
        return asJsonString(command);
    }


    protected <T> T asObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Error while deserializing from String", e);
            return null;
        }
    }

    protected String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error while serializing to String", e);
            return "{}";
        }
    }


}
