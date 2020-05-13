package hotelmanage;

public class RoomConditionChanged extends AbstractEvent {

//    private Long id;
    private Integer roomNumber;
    private String roomStatus;
//    private Integer roomScore;
//    private Integer roomScoreCnt;

    public RoomConditionChanged(){
        super();
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
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
