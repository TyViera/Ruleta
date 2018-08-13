package pe.edu.unp.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.unp.domain.BetNumber;

public interface BetNumberRepository extends CrudRepository<BetNumber, Long> {

    public BetNumber findByNumber(Integer number);

}
