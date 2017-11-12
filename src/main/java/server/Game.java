package server;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.time.Clock;

@Entity
@Table(name="game")
@XmlRootElement(name = "responsegame")
@NamedQueries({
        @NamedQuery(
                name = "get_open_game",
                query = "from Game where player2_id = null"
        )
})

public class Game {
    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name="sqlite", table="sqlite_sequence",pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "game", initialValue=1, allocationSize=1)
    @XmlElement(name="game_id")
    @Column(name="game_id")
    private Integer game_id;
    @XmlElement(name="player1_id")
    @Column(name="player1_id")
    private Integer player1_id;
    @XmlElement(name="player2_id")
    @Column(name="player2_id")
    private Integer player2_id;
    @XmlElement(name="time_start")
    @Column(name="time_start")
    private final String time_start = new Timestamp(System.currentTimeMillis()).toString();
    @XmlElement(name="time_end")
    @Column(name="time_end")
    private String time_end;
    @XmlElement(name = "end_code")
    @Column(name = "end_code")
    private Integer end_code;

    Game() {} //empty constructor for hibernate

    Game(Integer game_id){
        this.game_id = game_id;
    }

    Game(Integer player1_id,Integer player2_id){
        this.player1_id = player1_id;
        this.player2_id =player2_id;
    }

    Game(Player player){
        this.player1_id = player.getPlayer_id();
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

    public Integer getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(Integer player1_id) {
        this.player1_id = player1_id;
    }

    public Integer getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(Integer player2_id) {
        this.player2_id = player2_id;
    }
}


