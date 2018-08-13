package pe.edu.unp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unp.domain.Bet;
import pe.edu.unp.domain.BetNumber;
import pe.edu.unp.enums.BetTypeEnum;
import pe.edu.unp.enums.ColourEnum;
import pe.edu.unp.repository.BetNumberRepository;
import pe.edu.unp.repository.BetRepository;
import pe.edu.unp.repository.UserBetRepository;
import pe.edu.unp.repository.UserRepository;
import pe.edu.unp.service.StartService;
import pe.edu.unp.util.Constants;

@Service
public class StartServiceImpl implements StartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBetRepository userBetRepository;

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private BetNumberRepository betNumberRepository;

    @Override
    public void deleteAllData() {
        userRepository.deleteAll();
        userBetRepository.deleteAll();
        betRepository.deleteAll();
        betNumberRepository.deleteAll();
    }

    @Override
    public void initRouletteData() {
        //star the roulette
        Integer redNumbers[] = new Integer[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        BetNumber greenBetNumber;
        greenBetNumber = new BetNumber();
        greenBetNumber.setNumber(0);
        greenBetNumber.setColour(ColourEnum.GREEN.getValue());
        betNumberRepository.save(greenBetNumber);
        IntStream.rangeClosed(1, Constants.FULL_ROULETTE)
                .forEach(index -> {
                    BetNumber betNumber;
                    betNumber = new BetNumber();
                    betNumber.setNumber(index);
                    if (Arrays.asList(redNumbers).contains(index)) {
                        betNumber.setColour(ColourEnum.RED.getValue());
                    } else {
                        betNumber.setColour(ColourEnum.BLACK.getValue());
                    }
                    betNumberRepository.save(betNumber);
                });

        //star posible bets
        String names[] = new String[]{
            "Simple", "Doble", "Triple",
            "Cuadruple", "Sextuple", "Docena",
            "Rojo o negro", "Par o impar", "Bajo o alto"
        };
        BetTypeEnum types[] = new BetTypeEnum[]{
            BetTypeEnum.NUMBER, BetTypeEnum.NUMBER, BetTypeEnum.NUMBER,
            BetTypeEnum.NUMBER, BetTypeEnum.NUMBER, BetTypeEnum.NUMBER,
            BetTypeEnum.COLOUR, BetTypeEnum.EVEN_ODD, BetTypeEnum.HALF
        };
        Integer betCounts[] = new Integer[]{
            1, 2, 3,
            4, 6, 12,
            1, 1, 1
        };
        Integer multipliers[] = new Integer[]{
            35, 17, 11,
            8, 5, 2,
            2, 2, 2
        };

        IntStream.range(0, types.length)
                .forEach(index -> {
                    Bet bet;
                    bet = new Bet();
                    bet.setBetCount(betCounts[index]);
                    bet.setMultiplier(multipliers[index]);
                    bet.setName(names[index]);
                    bet.setType(types[index].getValue());
                    betRepository.save(bet);
                });
    }

}
