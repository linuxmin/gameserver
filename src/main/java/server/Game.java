package server;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="game")
@XmlRootElement(name = "game")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "get_open_game",
                query = "select max (game_id) from Game"
        ),

        @NamedQuery(
                name = "get_game_id_player",
                query = "from Game where winner_id = :id or player2_id =:id"
        )


})

public class Game extends DBObject {
    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name="sqlite", table="sqlite_sequence",pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "game", initialValue=1, allocationSize=1)
    @XmlElement(name="game_id")
    @Column(name="game_id")
    private Integer game_id;
    @XmlElement(name="winner_id")
    @Column(name="winner_id")
    private Integer winner_id;
    @XmlElement(name="time_start")
    @Column(name="time_start")
    private final String time_start = new Timestamp(System.currentTimeMillis()).toString();
    @XmlElement(name="time_end")
    @Column(name="time_end")
    private String time_end;
    @XmlElement(name = "end_code")
    @Column(name = "end_code")
    private Integer end_code;

    @XmlElement(name="players_no")
    @Column(name="players_no")
    private Integer players_no=0;


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="game_id")
    private List<Player> players = new ArrayList<>();

    Game() {} //empty constructor for hibernate

    Game(Integer game_id){
        this.game_id = game_id;
    }



    Game( String time_end, Integer end_code){
        this.end_code = end_code;
        this.time_end =  new Timestamp(System.currentTimeMillis()).toString();

    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public Integer getEnd_code() {
        return end_code;
    }

    public void setEnd_code(Integer end_code) {
        this.end_code = end_code;
    }

    public Integer getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Integer winner_id) {
        this.winner_id = winner_id;
    }

    public Integer getPlayers_no() {
        return players_no;
    }

    public void setPlayers_no() {
        this.players_no = this.players_no + 1;
    }

    public List<Player> getPlayer(){
        return this.players;
    }



}


