package huyvh.becommerce.dto.request;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class OrderDetailsRequest {

    private BigDecimal price;

    private Integer quantity;

    private String productId;

    private String orderId;
}
