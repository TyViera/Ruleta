/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unp.service.RouletteService;
import pe.edu.unp.util.BackendResponse;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RouletteService rouletteService;

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
}
