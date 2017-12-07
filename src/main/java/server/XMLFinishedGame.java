package server;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XMLFinishedGame")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLFinishedGame {
    @XmlElement
    public ActionMove actionMove10;

    @XmlElement
    public ActionMove actionMove20;

    @XmlElement(name="winner_id")
    public Integer winner_id;

    public ActionMove getActionMove10() {
        return actionMove10;
    }

    public void setActionMove10(ActionMove actionMove10) {
        this.actionMove10 = actionMove10;
    }

    public ActionMove getActionMove20() {
        return actionMove20;
    }

    public void setActionMove20(ActionMove actionMove20) {
        this.actionMove20 = actionMove20;
    }

    public Integer getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Integer winner_id) {
        this.winner_id = winner_id;
    }
}
