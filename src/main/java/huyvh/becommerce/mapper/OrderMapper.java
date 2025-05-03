package huyvh.becommerce.mapper;

import huyvh.becommerce.dto.request.OrderRequest;
import huyvh.becommerce.dto.response.OrderResponse;
import huyvh.becommerce.model.Order;
import huyvh.becommerce.model.User;

public class OrderMapper {
    public static Order addConvertToOrder(OrderRequest request, User user) {
        Order order = Order.builder()
                .user(user)
                .totalPrice(request.getTotalPrice())
                .totalQuantity(request.getTotalQuantity())
                .paymentMethod(request.getPaymentMethod())
                .status(request.getStatus())
                .buyAt(request.getBuyAt())
                .address(request.getAddress())
                .build();
        return order;
    }

    public static Order updateConvertToOrder(OrderRequest request, User user, Long id) {
        Order order = Order.builder()
                .id(id)
                .user(user)
                .totalPrice(request.getTotalPrice())
                .totalQuantity(request.getTotalQuantity())
                .paymentMethod(request.getPaymentMethod())
                .status(request.getStatus())
                .buyAt(request.getBuyAt())
                .address(request.getAddress())
                .build();
        return order;
    }

    public static OrderResponse convertToOrderResponse(Order order) {
        OrderResponse response = OrderResponse.builder()
                .id(order.getId())
                .userId(String.valueOf(order.getUser().getId()))
                .userName(order.getUser().getName())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .paymentMethod(order.getPaymentMethod())
                .status(order.getStatus())
                .buyAt(order.getBuyAt())
                .build();
        return response;
    }

}
