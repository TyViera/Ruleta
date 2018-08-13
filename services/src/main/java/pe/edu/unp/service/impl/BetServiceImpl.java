package pe.edu.unp.service.impl;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unp.domain.Bet;
import pe.edu.unp.domain.BetNumber;
import pe.edu.unp.domain.User;
import pe.edu.unp.domain.UserBets;
import pe.edu.unp.enums.BetTypeEnum;
import pe.edu.unp.enums.EvenEnum;
import pe.edu.unp.enums.HalfEnum;
import pe.edu.unp.repository.BetNumberRepository;
import pe.edu.unp.repository.BetRepository;
import pe.edu.unp.repository.UserBetRepository;
import pe.edu.unp.repository.UserRepository;
import pe.edu.unp.service.BetService;
import pe.edu.unp.util.BackendResponse;
import pe.edu.unp.util.Constants;

@Service
public class BetServiceImpl extends BaseServiceImpl<Bet, Long> implements BetService {

    @Autowired
    private UserBetRepository userBetRepository;

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private BetNumberRepository betNumberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public BetServiceImpl(BetRepository repository) {
        super(repository);
    }

    @Override
    public BackendResponse createBetForUser(UserBets userBet) {
        Integer luckyNumber, wonPoints, multiplier, numberSal;
        BackendResponse backendResponse;
        String split[], selValue;
        User findUserByUsername;
        BetNumber betNumber;
        Boolean wonGame;
        Bet bet;

        bet = betRepository.findById(userBet.getBet().getId()).orElse(null);

        findUserByUsername = userRepository.findUserByUsername(userBet.getUser().getUsername());
        findUserByUsername.setPlays(findUserByUsername.getPlays() + 1);
        findUserByUsername.setPoints(findUserByUsername.getPoints() - userBet.getBetPoints());

        multiplier = 0;
        wonGame = null;
        luckyNumber = randomValue();
        if (bet.getType().equals(BetTypeEnum.NUMBER.getValue())) {
            //number
            split = userBet.getSelectedBet().split(",");
            numberSal = Arrays.stream(split)
                    .map(x -> Integer.valueOf(x))
                    .filter(x -> (Objects.equals(x, luckyNumber)))
                    .findFirst()
                    .orElse(-1);
            wonGame = (numberSal >= 0);
            if (wonGame) {
                multiplier = bet.getMultiplier();
            }
        } else if (bet.getType().equals(BetTypeEnum.COLOUR.getValue())) {
            selValue = userBet.getSelectedBet();
            betNumber = betNumberRepository.findByNumber(luckyNumber);
            wonGame = betNumber.getColour().equals(selValue);
            if (wonGame) {
                multiplier = bet.getMultiplier();
            }
        } else if (bet.getType().equals(BetTypeEnum.EVEN_ODD.getValue())) {
            betNumber = betNumberRepository.findByNumber(luckyNumber);
            selValue = userBet.getSelectedBet();
            if (selValue.equals(EvenEnum.EVEN.getValue())) {
                wonGame = betNumber.isEven();
            } else if (selValue.equals(EvenEnum.ODD.getValue())) {
                wonGame = betNumber.isOdd();
            }
            if (wonGame) {
                multiplier = bet.getMultiplier();
            }
        } else if (bet.getType().equals(BetTypeEnum.HALF.getValue())) {
            betNumber = betNumberRepository.findByNumber(luckyNumber);
            selValue = userBet.getSelectedBet();
            if (selValue.equals(HalfEnum.HIGH.getValue())) {
                wonGame = betNumber.isHigh();
            } else if (selValue.equals(HalfEnum.LOW.getValue())) {
                wonGame = betNumber.isLow();
            }
            if (wonGame) {
                multiplier = bet.getMultiplier();
            }
        }

        wonPoints = userBet.getBetPoints() * multiplier;

        if (wonGame != null) {
            if (wonGame) {
                //won this bet
                findUserByUsername.setWonGames(findUserByUsername.getWonGames() + 1);
            } else {
                //didn't win
                findUserByUsername.setLostGames(findUserByUsername.getLostGames() + 1);
            }
        }
        findUserByUsername.setPoints(findUserByUsername.getPoints() + wonPoints);
        userRepository.save(findUserByUsername);

        userBet.setLuckyNumber(luckyNumber);
        userBet.setWonPoints(wonPoints);
        userBet.setUser(findUserByUsername);
        userBetRepository.save(userBet);

        backendResponse = BackendResponse.createFromSuccessMessage("El número sorteado fue: " + luckyNumber);
        backendResponse.setInfo(userBet);
        return backendResponse;
    }

    private Integer randomValue() {
        return (new Random()).nextInt(Constants.FULL_ROULETTE + 1);
    }

}
