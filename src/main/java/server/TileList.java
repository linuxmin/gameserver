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
import java.sql.Timestamp;
import java.util.List;

//@Entity
//@Table
@XmlRootElement(name = "tiles")
@XmlAccessorType(XmlAccessType.FIELD)
/*@NamedQueries({
        @NamedQuery(
                name = "get_tile_by_player_id",
                query = "select tile_id from Tile where player_id = :id"
        )
})*/
public class TileList {
    @XmlElement(name="time_end_generation")
    private Long time_end_generation;

    @XmlElement(name="player_id")
    private Integer player_id;

    @XmlElement(name="tile")
    private List<Tile> tiles;


    public List<Tile> getTiles(){
        return tiles;
    }

    public void setTiles(List<Tile> tiles){
        this.tiles = tiles;
    }

    public TileList(){}

    public Long getTime_end_generation() {
        return time_end_generation;
    }

    public void setTime_end_generation(Long time_end_generation) {
        this.time_end_generation = time_end_generation;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }
}
