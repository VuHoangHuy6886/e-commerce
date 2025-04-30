package huyvh.becommerce.repo;

import huyvh.becommerce.model.BannerWebsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerWebsiteRepo extends JpaRepository<BannerWebsite, Long> {
}
