package huyvh.becommerce.dto.response;


import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private String userId;

    private String userName;

    private String address;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private String paymentMethod;

    private String buyAt;

    private String status;

}
