package huyvh.becommerce.dto.response;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailsResponse {
    private Long id;

    private BigDecimal price;

    private Integer quantity;

    private String status;

    private String userId;

    private String userName;

    private String productId;

    private String productName;
}
