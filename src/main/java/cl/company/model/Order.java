package cl.company.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Double totalAmount;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private String createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    // Builder
    public static class Builder {
        private String userName;
        private Double totalAmount;
        private List<OrderDetail> orderDetails;

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder withOrderDetails(List<OrderDetail> orderDetails) {
            this.orderDetails = orderDetails;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setUserName(userName);
            order.setTotalAmount(totalAmount);
            order.setOrderDetails(orderDetails);
            return order;
        }
    }
}
