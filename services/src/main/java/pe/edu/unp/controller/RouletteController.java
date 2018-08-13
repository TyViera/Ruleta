package pe.edu.unp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unp.service.BetNumberService;
import pe.edu.unp.service.BetService;
import pe.edu.unp.service.RouletteService;
import pe.edu.unp.util.BackendResponse;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RouletteService rouletteService;

    @Autowired
    private BetNumberService betNumberService;

    @Autowired
    private BetService betService;

    @GetMapping("/chair/{username}")
    public BackendResponse getChairFromUsername(@PathVariable String username) {
        BackendResponse response;
        try {
            Integer chair = rouletteService.getChair(username);
            if (chair < 0) {
                //fail
                response = BackendResponse.createFromErrorMessage("Mesa llena, no hay cupo disponible, por favor intentelo más tarde");
            } else {
                response = BackendResponse.createFromInfo(chair);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response = BackendResponse.createFromErrorMessage("Ocurrio un error al obtener la silla de juego.");
        }
        return response;
    }

    @GetMapping("/game")
    public BackendResponse getRouletteForGame() {
        BackendResponse response;
        try {
            response = BackendResponse.createFromInfo(betNumberService.getAll());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response = BackendResponse.createFromErrorMessage("Ocurrio un error al obtener la información de juego.");
        }
        return response;
    }

    @GetMapping("/bets")
    public BackendResponse getBetsForGame() {
        BackendResponse response;
        try {
            response = BackendResponse.createFromInfo(betService.getAll());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response = BackendResponse.createFromErrorMessage("Ocurrio un error al obtener los tipos de apuesta disponibles.");
        }
        return response;
    }

}
