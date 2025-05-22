package huyvh.becommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String status;

    private String categoryId;

    private String categoryName;

    private String describe;

    private String urlImage;
}
