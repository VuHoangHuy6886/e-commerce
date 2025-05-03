package huyvh.becommerce.dto.request;

import huyvh.becommerce.model.Category;
import lombok.Getter;

@Getter

public class ProductRequest {

    private String name;

    private Double price;

    private Integer quantity;

    private String status;

    private String category;

    private String describe;
}
