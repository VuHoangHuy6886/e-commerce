package huyvh.becommerce.mapper;

import huyvh.becommerce.dto.request.ProductRequest;
import huyvh.becommerce.dto.response.ProductResponse;
import huyvh.becommerce.model.Category;
import huyvh.becommerce.model.Product;

public class ProductMapper {
    public static Product addConvertToProduct(ProductRequest request,Category category) {
        Product product = Product.builder()
                .category(category)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .status(request.getStatus())
                .urlImage(request.getUrlImage())
                .build();
        return product;
    }

    public static Product updateConvertToProduct(ProductRequest request, Category category, Long id) {
        Product product = Product.builder()
                .id(id)
                .category(category)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .urlImage(request.getUrlImage())
                .status(request.getStatus())
                .build();
        return product;
    }

    public static ProductResponse ConvertToProductResponse(Product product) {
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getStatus())
                .categoryId(String.valueOf(product.getCategory().getId()))
                .categoryName(product.getCategory().getName())
                .describe(product.getDescription())
                .urlImage(product.getUrlImage())
                .build();
        return response;
    }
}
