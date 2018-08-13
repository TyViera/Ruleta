package pe.edu.unp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unp.domain.BetNumber;
import pe.edu.unp.repository.BetNumberRepository;
import pe.edu.unp.service.BetNumberService;

@Service
public class BetNumberServiceImpl extends BaseServiceImpl<BetNumber, Long> implements BetNumberService {

    @Autowired
    public BetNumberServiceImpl(BetNumberRepository repository) {
        super(repository);
    }

}
