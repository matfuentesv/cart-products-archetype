package cl.company.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String userName;
    private List<ProductRequest> products;

    @Data
    public static class ProductRequest {
        private String productName;
        private Double price;
        private Integer quantity;
    }
}
