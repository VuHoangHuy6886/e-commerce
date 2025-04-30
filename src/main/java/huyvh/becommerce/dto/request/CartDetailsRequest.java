package huyvh.becommerce.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartDetailsRequest {

    private BigDecimal price;

    private Integer quantity;

    private String status;

    private String productId;

    private String userId;
}
