package server; /**
 * Created by laptop on 06.11.17.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;


@XmlRootElement(name = "Error")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error{

    @XmlElement(name="error_id")
    private Integer error_id;

    @XmlElement(name="message")
    private String message;

    @XmlElement(name="timestamp")
    private String timestamp = new Timestamp(System.currentTimeMillis()).toString();

    Error(){}; //default constructor for Hibernate

    public Error(String message){
        this.message = message;
    }

    public Integer getError_id() {
        return error_id;
    }

    public void setError_id(Integer error_id) {
        this.error_id = error_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
