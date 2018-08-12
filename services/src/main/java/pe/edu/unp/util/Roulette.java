package pe.edu.unp.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pe.edu.unp.domain.User;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Roulette {

    private Long startLong;
    private Long endLong;
    private Integer capacity;
    private List<User> usersPlay;

}
