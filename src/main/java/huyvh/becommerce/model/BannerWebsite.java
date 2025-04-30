package huyvh.becommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_banner_website")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BannerWebsite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;
}
