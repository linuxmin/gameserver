package server; /**
 * Created by laptop on 06.11.17.
 */

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlRootElement(name = "registernewplayer")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "get_player_by_first_name",
                query = "from Player where first_name = :name"
        ),

        @NamedQuery(
                name = "get_player_by_id",
                query = "from Player where player_id = :id"
        )
})
public class Player extends DBObject {
    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name="sqlite", table="sqlite_sequence",pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Player", initialValue=1, allocationSize=1)
    @XmlElement(name="player_id")
    @Column(name="player_id")
    private Integer player_id;

    @XmlElement(name="first_name")
    @Column(name="first_name")
    private String first_name;

    @XmlElement(name="last_name")
    @Column(name="last_name")
    private String last_name;

    @XmlElement(name="age")
    @Column(name="age")
    private Integer age;

    @XmlElement(name="nickname")
    @Column(name="nickname")
    private String nickname;

    @XmlElement(name="game_id")
    @Column(name="game_id")
    private Integer game_id;




    @Transient
    private Integer map_id;

    Player(){}  //default constructor for hibernate

    Player(Game game) {

    }

    Player(Integer player_id, String first_name, String last_name, Integer age, String nickname){
   //     init(player_id,first_name,last_name,age,nickname);
        this.player_id = player_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.nickname = nickname;
    }

    private void init(Integer player_id, String first_name, String last_name, Integer age, String nickname){
        this.player_id = player_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.nickname = nickname;
    }

    public Integer getPlayer_id(){
        return this.player_id;
    }

    public String getFirst_name(){
        return this.first_name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public Integer getAge(){
        return this.age;
    }

    public String getNickname(){
        return this.nickname;
    }

    public Integer getGame_id() {
       return game_id;
   }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Integer getMap_id() {
        return map_id;
    }

    public void setMap_id(Integer map_id) {
        this.map_id = map_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPlayerProperties(Player player){
        this.first_name = player.first_name;
        this.last_name = player.last_name;
        this.age = player.age;
        this.nickname = player.nickname;
    }
}
