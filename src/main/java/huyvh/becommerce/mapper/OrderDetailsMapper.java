package huyvh.becommerce.mapper;

import huyvh.becommerce.dto.request.OrderDetailsRequest;
import huyvh.becommerce.dto.response.OrderDetailsResponse;
import huyvh.becommerce.model.Order;
import huyvh.becommerce.model.OrderDetails;
import huyvh.becommerce.model.Product;

public class OrderDetailsMapper {
    public static OrderDetails addConvertToOrderDetails(OrderDetailsRequest request, Order order, Product product) {
        OrderDetails orderDetails = OrderDetails.builder()
                .order(order)
                .product(product)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        return orderDetails;
    }

    public static OrderDetails addConvertToOrderDetails(OrderDetailsRequest request, Order order, Product product, Long id) {
        OrderDetails orderDetails = OrderDetails.builder()
                .id(id)
                .order(order)
                .product(product)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        return orderDetails;
    }

    public static OrderDetailsResponse convertToOrderDetailsResponse(OrderDetails orderDetails) {
        OrderDetailsResponse response = OrderDetailsResponse.builder()
                .id(orderDetails.getId())
                .price(orderDetails.getPrice())
                .productId(String.valueOf(orderDetails.getProduct().getId()))
                .productName(orderDetails.getProduct().getName())
                .quantity(orderDetails.getQuantity())
                .orderId(String.valueOf(orderDetails.getOrder().getId()))
                .build();
        return response;
    }

}
