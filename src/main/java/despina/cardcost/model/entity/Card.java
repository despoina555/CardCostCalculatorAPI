package despina.cardcost.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CARD")
public @Data class Card {
    @Id @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "COST")
    private BigDecimal cost;

}
