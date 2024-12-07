package cl.company.service.impl;

import cl.company.model.Order;
import cl.company.model.OrderDetail;
import cl.company.model.OrderRequest;
import cl.company.model.OrderResponse;
import cl.company.repository.OrderDetailRepository;
import cl.company.repository.OrderRepository;
import cl.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) {

        double totalAmount = orderRequest.getProducts().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();


        Order order = new Order.Builder()
                .withUserName(orderRequest.getUserName())
                .withTotalAmount(totalAmount)
                .build();
        orderRepository.save(order);


        for (OrderRequest.ProductRequest product : orderRequest.getProducts()) {
            OrderDetail detail = new OrderDetail.Builder()
                    .withOrder(order)
                    .withProductName(product.getProductName())
                    .withPrice(product.getPrice())
                    .withQuantity(product.getQuantity())
                    .build();
            orderDetailRepository.save(detail);
        }
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            List<OrderResponse.ProductResponse> products = order.getOrderDetails().stream()
                    .map(detail -> new OrderResponse.ProductResponse(
                            detail.getProductName(),
                            detail.getPrice(),
                            detail.getQuantity()))
                    .collect(Collectors.toList());
            return new OrderResponse(order.getId(), order.getUserName(), order.getTotalAmount(), products);
        }).collect(Collectors.toList());
    }
}
