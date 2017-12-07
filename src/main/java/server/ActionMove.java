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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="action")
@XmlRootElement(name = "sendaction")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "get_last_move",
                query = "select max (action_id) from ActionMove"
        ),
        @NamedQuery(
                name = "count_actions",
                query = "SELECT count (action_id) from ActionMove where game_id = :game_id"
        ),

        @NamedQuery(
                name = "get_actions_by_game_id",
                query =  "from ActionMove where game_id =:game_id"
        ),

        @NamedQuery(
                name = "get_last_move_player",
                query = "select max (action_id) from ActionMove where player_id = :player_id"
        )


})

public class ActionMove {
    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name = "sqlite", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Action", initialValue = 1, allocationSize = 1)
    @XmlElement(name = "action_id")
    @Column(name = "action_id")
    private Integer action_id;

    @XmlElement(name = "player_id")
    @Column(name = "player_id")
    private Integer player_id;

    @XmlElement(name = "game_id")
    @Column(name = "game_id")
    private Integer game_id;

    @XmlElement(name="map_id")
    //@Transient
    @Column(name="map_id")
    private Integer map_id;

    @XmlElement(name="time_start")
    @Column(name="time_start")
    private Long time_start;

    @XmlElement(name="time_end")
    @Column(name="time_end")
    private Long time_end;
    

    @XmlElement(name = "start_tile")
    @Column(name = "start_tile")
    private Integer start_tile;

    @XmlElement(name = "target_tile")
    @Column(name = "target_tile")
    private Integer target_tile;

    @XmlElement(name = "actions_left")
    @Column(name = "actions_left")
    private Integer actions_left;

    @XmlElement(name = "inventory")
    @Column(name = "inventory")
    private Integer inventory = 0;

    @XmlElement(name = "won")
    @Column(name = "won")
    private Integer won = 0;


    ActionMove() {
    }  //default constructor for hibernate

    ActionMove(Game game){
        this.game_id = game.getGame_id();
    }

    public Integer getAction_id() {
        return action_id;
    }

    public void setAction_id(Integer action_id) {
        this.action_id = action_id;
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

    public Long getTime_start() {
        return time_start;
    }

    public void setTime_start(Long time_start) {
        this.time_start = time_start;
    }

    public Long getTime_end() {
        return time_end;
    }

    public void setTime_end(Long time_end) {
        this.time_end = time_end;
    }

    public Integer getStart_tile() {
        return start_tile;
    }

    public void setStart_tile(Integer start_tile) {
        this.start_tile = start_tile;
    }

    public Integer getTarget_tile() {
        return target_tile;
    }

    public void setTarget_tile(Integer target_tile) {
        this.target_tile = target_tile;
    }

    public Integer getActions_left() {
        return actions_left;
    }

    public void setActions_left(Integer actions_left) {
        this.actions_left = actions_left;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public void setProperties(ActionMove actionMove){
        this.setGame_id(actionMove.getGame_id());
        this.setWon(actionMove.getWon());
        this.setInventory(actionMove.getInventory());
        this.setActions_left(actionMove.getActions_left());
        this.setMap_id(actionMove.getMap_id());
        this.setTarget_tile(actionMove.getTarget_tile());
        this.setStart_tile(actionMove.getStart_tile());
        this.setTime_start(actionMove.getTime_start());
        this.setTime_end(actionMove.getTime_end());
        this.setPlayer_id(actionMove.getPlayer_id());
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public boolean checkSeconds(){
        Long diff;
        Timestamp start = new Timestamp(this.getTime_start());
        Timestamp end = new Timestamp(this.getTime_end());
        if(this.getTime_start() != null && this.getTime_end() != null){
            diff = (end.getTime()- start.getTime())/1000;
            if(diff > 3){
                return false;
            }
            return true;
        }
        return false;
    }
}
