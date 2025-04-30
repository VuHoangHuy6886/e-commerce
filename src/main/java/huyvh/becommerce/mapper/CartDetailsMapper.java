package huyvh.becommerce.mapper;

import huyvh.becommerce.dto.request.CartDetailsRequest;
import huyvh.becommerce.dto.response.CartDetailsResponse;
import huyvh.becommerce.model.CartDetails;
import huyvh.becommerce.model.Product;
import huyvh.becommerce.model.User;

public class CartDetailsMapper {
    public static CartDetails addConvertToCartDetails(CartDetailsRequest request, Product product, User user) {
        CartDetails cartDetails = CartDetails.builder()
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .product(product)
                .user(user)
                .status(request.getStatus())
                .build();
        return cartDetails;
    }

    public static CartDetails updateConvertToCartDetails(CartDetailsRequest request, Product product, User user, Long id) {
        CartDetails cartDetails = CartDetails.builder()
                .id(id)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .product(product)
                .user(user)
                .status(request.getStatus())
                .build();
        return cartDetails;
    }

    public static CartDetailsResponse convertToCartDetailsResponse(CartDetails cartDetails) {
        CartDetailsResponse response = CartDetailsResponse.builder()
                .id(cartDetails.getId())
                .price(cartDetails.getPrice())
                .quantity(cartDetails.getQuantity())
                .status(cartDetails.getStatus())
                .userId(String.valueOf(cartDetails.getUser().getId()))
                .userName(cartDetails.getUser().getName())
                .productId(String.valueOf(cartDetails.getProduct().getId()))
                .productName(cartDetails.getProduct().getName())
                .build();
        return response;
    }
}
