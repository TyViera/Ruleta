package pe.edu.unp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "bets")
public class Bet implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private Integer betCount;
    
    private String type;
    
    private Integer multiplier;
    
    @OneToMany(mappedBy = "bet")
    private List<UserBets> bets;

    public Bet(Long id) {
        this.id = id;
    }

}
