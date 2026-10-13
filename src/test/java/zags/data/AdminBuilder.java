package zags.data;

import zags.api.models.AdminProcessRequest;
import zags.api.models.AdminRequest;

public class AdminBuilder {
    public static AdminProcessRequest adminProcessRequest(Integer applId, Integer staffId, String  action){
        return  AdminProcessRequest.builder()
                .applId(applId)
                .staffId(staffId)
                .action(action)
                .build();
    }
    public  static AdminRequest admin(){
        return AdminRequest.builder()
                .personalFirstName("Fedor")
                .personalLastName("Fedorov")
                .personalMiddleName("Borisovich")
                .personalPhoneNumber("123456")
                .personalNumberOfPassport("Ba12345")
                .dateofbirth("2001-06-11")
                .build();
    }
}
