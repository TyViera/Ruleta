package pe.edu.unp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pe.edu.unp.service.StartService;

@SpringBootApplication
public class RuletaServicesApp {

    private static final Logger logger = LoggerFactory.getLogger(RuletaServicesApp.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RuletaServicesApp.class, args);
        StartService startService = run.getBean(StartService.class);
        logger.info("DELETE ALL DATA FROM TABLES");
        startService.deleteAllData();
        logger.info("INIR ROULETTE DATA FROM SEED");
        startService.initRouletteData();
    }
}
