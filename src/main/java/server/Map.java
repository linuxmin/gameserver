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

@Entity
@Table
@XmlRootElement(name = "registernewmap")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "get_map_by_player_id",
                query = "from Map where player_id = :name"
        )
})
public class Map extends DBObject{
    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name="sqlite", table="sqlite_sequence",pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Map", initialValue=1, allocationSize=1)
    @XmlElement(name="map_id")
    @Column(name="map_id")
    private Integer map_id;

    @XmlElement(name="game_id")
    @Column(name="game_id")
    private Integer game_id;

    @XmlElement(name="player_id")
    @Column(name="player_id")
    private Integer player_id;



    Map(){}  //default constructor for hibernate

    public Map(Integer map_id, Integer game_id, Integer player_id) {
        this.map_id = map_id;
        this.game_id = game_id;
        this.player_id = player_id;
    }

    public Integer getMap_id() {
        return map_id;
    }

    public void setMap_id(Integer map_id) {
        this.map_id = map_id;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }
}
