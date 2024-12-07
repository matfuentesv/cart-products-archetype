package cl.company.service;

import cl.company.model.OrderRequest;
import cl.company.model.OrderResponse;

import java.util.List;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
}
