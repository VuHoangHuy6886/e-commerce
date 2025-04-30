package huyvh.becommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;

    private String email;

    private String password;

    private String name;

    private Boolean gender;

    private String address;

    private String phone;

    private String refreshToken;

    private String roles;
}
