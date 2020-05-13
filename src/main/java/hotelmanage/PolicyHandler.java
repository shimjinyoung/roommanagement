package hotelmanage;

import hotelmanage.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PolicyHandler{

    @Autowired
    RoomManagementRepository roomManagementRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCheckedOut_객실정비요청(@Payload CheckedOut checkedOut) throws InterruptedException {

        if(checkedOut.isMe()){
            System.out.println("##### listener 객실정비요청 : " + checkedOut.toJson());

            if(roomManagementRepository.findById(checkedOut.getRoomNumber()) != null && "checkOut".equals(checkedOut.getReserveStatus())){
                RoomManagement roomManagement = new RoomManagement();
                roomManagement.setRoomNumber(checkedOut.getRoomNumber());
                /*roomManagement.setRoomStatus("정비중");

                TimeUnit.MINUTES.sleep(1);
                roomManagementRepository.save(roomManagement);*/
                //정비 1분 소요 후 예약가능 상태로 변경
                roomManagement.setRoomStatus("RoomAvailable");
                roomManagementRepository.save(roomManagement);
            }
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReserved_객실상태변경(@Payload Reserved reserved){

        if(reserved.isMe()){
            System.out.println("##### listener 객실상태변경 : " + reserved.toJson());

            if(roomManagementRepository.findById(reserved.getRoomNumber()) != null && "reserve".equals(reserved.getReserveStatus())){
                RoomManagement roomManagement = new RoomManagement();
                roomManagement.setRoomNumber(reserved.getRoomNumber());
                roomManagement.setRoomStatus("RoomNotAvaliable");

                roomManagementRepository.save(roomManagement);
            }


        }
    }

}
