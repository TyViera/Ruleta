package pe.edu.unp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user_bets")
public class UserBets implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer luckyNumber;
    
    private Integer wonPoints;
    
    private Integer betPoints;
    
    private String selectedBet;

    @JsonIgnoreProperties("bets")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties("bets")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bets_id")
    private Bet bet;

    public UserBets(Long id) {
        this.id = id;
    }

}
