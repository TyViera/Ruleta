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

//    public Boolean getIsEven() {
//        return (number > 0) && (number % 2 == 0);
//    }
//
//    public Boolean getIsHalf() {
//        return (number > 0) && (number <= Constants.HALF_ROULETTE);
//    }

}
