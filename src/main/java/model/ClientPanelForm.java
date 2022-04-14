package model;

import enums.Country;
import enums.PolandProvince;
import lombok.Data;

@Data
public class ClientPanelForm {

    private String clientLogin;
    private String clientPassword;
    private String createLogin;
    private String createPassword;
    private String repeatPassword;
    private String clientFirstName;
    private String clientLastName;
    private String addressStreet;
    private String addressLocalNumber;
    private String postCode;
    private String cityName;
    private PolandProvince province;
    private Country country;
    private String emailAddress;
    private String phoneNumber;

}
