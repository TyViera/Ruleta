package pe.edu.unp.service;

import pe.edu.unp.domain.Bet;
import pe.edu.unp.domain.UserBets;
import pe.edu.unp.util.BackendResponse;

public interface BetService extends BaseService<Bet, Long> {

    public BackendResponse createBetForUser(UserBets userBet);
    
}
