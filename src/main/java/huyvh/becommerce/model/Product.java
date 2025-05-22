package huyvh.becommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String status;

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartDetails> cartDetails = new ArrayList<>();
}