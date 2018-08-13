package pe.edu.unp.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.unp.util.Constants;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bet_number")
public class BetNumber implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer number;

    private String colour;

    public Boolean isEven() {
        return (number > 0) && (number % 2 == 0);
    }

    public Boolean isOdd() {
        return (number > 0) && (number % 2 != 0);
    }

    public Boolean isLow() {
        return (0 < number) && (number <= Constants.HALF_ROULETTE);
    }

    public Boolean isHigh() {
        return (Constants.HALF_ROULETTE < number) && (number <= Constants.FULL_ROULETTE);
    }

}
