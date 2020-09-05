package despina.cardcost.model.entity;


import lombok.Data;

public @Data  class CardDetailsPayload {

    private String scheme;

    private String type;

    //private String bank;

    private Country country;

}
