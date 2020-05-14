package hotelmanage;


import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@Entity
@Table(name="BizPartnerManagement_table")
public class BizPartnerManagement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int roomNumber;
    private String roomStatus;

    @PrePersist
    public void onPrePersist() {

        if ("first".equals(roomStatus) ) {
            System.out.println("=============룸정보 등록 처리중=============");
         /*   PaymentCompleted paymentCompleted = new PaymentCompleted();

            setPaymentStatus("Y");
            paymentCompleted.setPaymentId(PaymentId);
            System.out.printf("PaymentId : %d\n",PaymentId);
            paymentCompleted.setReservationNumber(ReservationNumber);
            System.out.printf("ReservationNumber : %d\n",ReservationNumber);
            paymentCompleted.setPaymentPrice(PaymentPrice);
            System.out.printf("PaymentPrice : %d\n",PaymentPrice);
            paymentCompleted.setReservationStatus(ReservationStatus);
            System.out.printf("ReservationStatus : %s\n",ReservationStatus);
            paymentCompleted.setPaymentStatus(PaymentStatus);
            System.out.printf("PaymentStatus : %s\n",PaymentStatus);
            BeanUtils.copyProperties(this, paymentCompleted);
            paymentCompleted.publishAfterCommit();*/


            RoomConditionChanged roomConditionChanged = new RoomConditionChanged();
            roomConditionChanged.setRoomNumber(this.getRoomNumber());
            roomConditionChanged.setRoomStatus(this.getRoomStatus());
            BeanUtils.copyProperties(this, roomConditionChanged);
            roomConditionChanged.publishAfterCommit();

        }
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }



}

