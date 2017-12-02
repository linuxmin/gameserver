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
@XmlRootElement(name = "tile")
@XmlAccessorType(XmlAccessType.FIELD)
/*@NamedQueries({
        @NamedQuery(
                name = "get_tile_by_player_id",
                query = "select tile_id from Tile where player_id = :id"
        )
})*/
public class Tile {
    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name = "sqlite", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Tile", initialValue = 1, allocationSize = 1)
    @XmlElement(name = "tile_id")
    @Column(name = "tile_id")
    private Integer tile_id;

    @XmlElement(name = "type")
    @Column(name = "type")
    private Integer type;

    @XmlElement(name = "x")
    @Column(name = "x")
    private Integer x;

    @XmlElement(name = "y")
    @Column(name = "y")
    private Integer y;

    @XmlElement(name = "castle")
    @Column(name = "castle")
    private Integer castle;

    @XmlElement(name = "treasure")
    @Column(name = "treasure")
    private Integer treasure;


  /*  @XmlElement(name="game_id")
    @Column(name="game_id")
    private Integer game_id;

    @XmlElement(name="player_id")
    @Column(name="player_id")
    private Integer player_id;
*/

  @XmlElement(name="map_id")
   //@Transient
   @Column(name="map_id")
    private Integer map_id;

  /*@ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name="map_id")
            private Map mappingmap;
*/



    Tile() {
    }  //default constructor for hibernate

    public Integer getTile_id() {
        return tile_id;
    }

    public void setTile_id(Integer tile_id) {
        this.tile_id = tile_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getX() {
        return x;
    }
    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getCastle() {
        return castle;
    }

    public void setCastle(Integer castle) {
        this.castle = castle;
    }

    public Integer getTreasure() {
        return treasure;
    }

    public void setTreasure(Integer treasure) {
        this.treasure = treasure;
    }
    public Integer getMap_id() {
        return map_id;
    }

    public void setMap_id(Integer map_id) {
        this.map_id = map_id;
    }







   /*

      public void setMappingmap(Map map){
        this.mappingmap = map;
        map.getTiles().add(this);
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
        public Map getMap() {
        return tileslist;
    }

    public void setMap(Map map) {
        this.tileslist = map;
    }
*/




}
