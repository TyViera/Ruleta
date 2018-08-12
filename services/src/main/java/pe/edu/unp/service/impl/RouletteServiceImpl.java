package pe.edu.unp.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import pe.edu.unp.service.RouletteService;

@Service
public class RouletteServiceImpl implements RouletteService {

    private final Set<String> chairs;

    public RouletteServiceImpl() {
        chairs = new HashSet<>();
    }

    @Override
    public Integer getChair(String userName) {
        synchronized (chairs) {
            if (chairs.size() == 10) {
                return -1;
            } else {
                chairs.add(userName);
                return chairs.size();
            }
        }
    }

}
