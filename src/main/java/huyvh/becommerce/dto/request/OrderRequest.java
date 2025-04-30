package huyvh.becommerce.dto.request;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class OrderRequest {

    private String userId;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private String paymentMethod;

    private String status;

    private String buyAt;

    private String address;
}
