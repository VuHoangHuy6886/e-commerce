package huyvh.becommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private Boolean gender;

    private String address;

    private String phone;

    private String refreshToken;

    private String roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> userOrders = new ArrayList<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CartDetails> cartDetails = new ArrayList<>();
}
