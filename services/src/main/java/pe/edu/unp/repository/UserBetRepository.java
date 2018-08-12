package pe.edu.unp.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.unp.domain.UserBets;

public interface UserBetRepository extends CrudRepository<UserBets, Long> {
    
}
