package hotelmanage;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

@Entity
@Table(name="RoomManagement_table")
public class RoomManagement {

    @Id @GeneratedValue
    private Integer roomNumber;
    private String roomStatus;


    @PostPersist
    public void onPostPersist(){
        RoomConditionChanged roomConditionChanged = new RoomConditionChanged();
        roomConditionChanged.setRoomNumber(this.getRoomNumber());
        roomConditionChanged.setRoomStatus(this.getRoomStatus());
        BeanUtils.copyProperties(this, roomConditionChanged);
        roomConditionChanged.publishAfterCommit();
    }
    @PostUpdate
    public void onPostUpdate(){

//        if("RoomAvailable".equals(this.roomStatus)){
//            System.out.println("예약가능?:"+this.roomStatus);
            RoomConditionChanged roomConditionChanged = new RoomConditionChanged();
            roomConditionChanged.setRoomNumber(this.getRoomNumber());
            roomConditionChanged.setRoomStatus(this.getRoomStatus());
            BeanUtils.copyProperties(this, roomConditionChanged);
            roomConditionChanged.publishAfterCommit();
//            System.out.println("예약가능으로 변경");
//        }

    }


    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
    /*public Integer getRoomScore() {
        return roomScore;
    }

    public void setRoomScore(Integer roomScore) {
        this.roomScore = roomScore;
    }
    public Integer getRoomScoreCnt() {
        return roomScoreCnt;
    }

    public void setRoomScoreCnt(Integer roomScoreCnt) {
        this.roomScoreCnt = roomScoreCnt;
    }*/




}
