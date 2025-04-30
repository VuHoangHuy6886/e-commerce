package huyvh.becommerce.dto.response;


import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponse {
    private Long id;

    private BigDecimal price;

    private Integer quantity;

    private String productId;

    private String productName;

    private String orderId;
}
