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
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlRootElement(name = "registernewmap")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "get_map_by_player_id",
                query = "select map_id from Map where player_id = :id"
        ),
        @NamedQuery(
                name = "get_other_map",
                query ="from Map where game_id = :game_id and not player_id =:player_id"
        )
})
public class Map{
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

    @XmlElement(name="time_start_generation")
    @Column(name="time_start_generation")
    private Timestamp time_start_generation;



    @XmlElement(name="time_end_generation")
    @Column(name="time_end_generation")

    private Timestamp time_end_generation;

  //  @OneToMany(mappedBy = "mappingmap")
  //  private List<Tile> tiles;

    @OneToMany(cascade = CascadeType.PERSIST)
            @JoinColumn(name = "map_id")
            private List<Tile> tiles = new ArrayList<>();




    Map(){}  //default constructor for hibernate


    Map(Game game){
       // this.player_id = player.getPlayer_id();
       this.game_id = game.getGame_id();
    }

    Map(Player player){
        this.player_id = player.getPlayer_id();
        //this.map_id =  map.getMap_id();
    }

    public Map(Integer map_id, Integer game_id, Integer player_id) {
        this.map_id = map_id;
        this.game_id = game_id;
        this.player_id = player_id;
    }

   /* public Map(Tile tile){
        this.tiles.add(tile);
    }*/

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

    public List<Tile> getTiles(){
        return this.tiles;
    }

    public Timestamp getTime_start_generation() {
        return time_start_generation;
    }
    public void setTime_start_generation(Timestamp time_start_generation) {
        this.time_start_generation = time_start_generation;
    }

    public Timestamp getTime_end_generation() {
        return time_end_generation;
    }

    public void setTime_end_generation(Timestamp time_end_generation) {
        this.time_end_generation = time_end_generation;
    }

    public boolean checkSeconds(){
        Integer diff = 0;
        if(this.time_start_generation != null && this.time_end_generation != null){
            diff = this.time_end_generation.getSeconds() - this.time_end_generation.getSeconds();
            if(diff > 3){
                return false;
            }
            return true;
        }
        return false;
    }
}
