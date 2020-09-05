package despina.cardcost.repository;

import despina.cardcost.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String card_number);
}
