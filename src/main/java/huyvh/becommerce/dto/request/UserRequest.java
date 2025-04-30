package huyvh.becommerce.dto.request;


import lombok.Getter;

@Getter

public class UserRequest {

    private String email;

    private String password;

    private String name;

    private Boolean gender;

    private String address;

    private String phone;

    private String refreshToken;

    private String roles;
}
