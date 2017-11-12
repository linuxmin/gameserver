package server;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlRootElement(name = "responsenewplayer")

public class Play {

    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name = "sqlite", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Player", initialValue = 1, allocationSize = 1)
    @XmlElement(name = "play_id")
    @Column(name = "play_id")
    private Integer play_id;

    @XmlElement(name="player_id")
    @Column(name="player_id")
    private Integer player_id;

    @XmlElement(name="game_id")
    @Column(name="game_id")
    private Integer game_id;

    @OneToMany(mappedBy = "player_id", cascade = CascadeType.ALL)
    private List<Player> participants = new ArrayList<>();

    Play() {}
    Play(Integer play_id, Integer player_id,Integer game_id){
        this.play_id = play_id;
    }

    Play(Integer player_id, Integer game_id){
        this.player_id = player_id;
        this.game_id = game_id;
    }

    public Integer getPlay_id() {return this.play_id;}
    public List<Player> getParticipants() {return this.participants;}
}


