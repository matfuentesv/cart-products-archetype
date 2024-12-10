package cl.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String userName;
    private Double totalAmount;
    private List<ProductResponse> products;

    public OrderResponse() {

    }


    @Data
    @AllArgsConstructor
    public static class ProductResponse {
        private String productName;
        private Double price;
        private Integer quantity;
    }
}
