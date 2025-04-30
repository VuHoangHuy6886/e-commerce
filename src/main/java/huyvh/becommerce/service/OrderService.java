package huyvh.becommerce.service;

import huyvh.becommerce.dto.request.OrderRequest;
import huyvh.becommerce.dto.response.OrderResponse;
import huyvh.becommerce.mapper.OrderMapper;
import huyvh.becommerce.model.Order;
import huyvh.becommerce.model.User;
import huyvh.becommerce.repo.OrderRepo;
import huyvh.becommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    public OrderResponse add(OrderRequest orderRequest) {
        User user = userRepo.findById(Long.valueOf(orderRequest.getUserId())).orElseThrow(() -> new RuntimeException("user not found"));
        Order order = OrderMapper.addConvertToOrder(orderRequest, user);
        return OrderMapper.convertToOrderResponse(orderRepo.save(order));
    }

    public Page<OrderResponse> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orders = orderRepo.findAll(pageRequest);
        return orders.map(OrderMapper::convertToOrderResponse);
    }
}
