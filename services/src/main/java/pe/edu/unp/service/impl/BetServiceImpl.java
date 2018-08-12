package pe.edu.unp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unp.domain.Bet;
import pe.edu.unp.repository.BetRepository;
import pe.edu.unp.service.BetService;

@Service
public class BetServiceImpl extends BaseServiceImpl<Bet, Long> implements BetService {
    
    @Autowired
    public BetServiceImpl(BetRepository repository) {
        super(repository);
    }
    
}
