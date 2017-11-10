package server; /**
 * Created by laptop on 06.11.17.
 */
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table
@XmlRootElement(name = "player")
public class player {
    @Id
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

    player(){}  //default constructor for hibernate

    player(Integer player_id, String first_name, String last_name, Integer age, String nickname){
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
}
