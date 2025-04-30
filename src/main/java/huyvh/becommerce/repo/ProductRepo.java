package huyvh.becommerce.repo;

import huyvh.becommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    default Page<Product> searchProducts(
            String name,
            Double minPrice,
            Double maxPrice,
            String status,
            Long categoryId,
            String sortDirection,
            Pageable pageable
    ) {
        Specification<Product> spec = Specification.where(null);

        // Lọc theo tên (tìm kiếm gần đúng, không phân biệt hoa thường)
        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        // Lọc theo giá tối thiểu
        if (minPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        // Lọc theo giá tối đa
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        // Lọc theo trạng thái
        if (status != null && !status.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), status));
        }

        // Lọc theo danh mục
        if (categoryId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category").get("id"), categoryId));
        }

        // Sắp xếp
        if (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) {
            return findAll(spec,
                    PageRequest.of(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),
                            Sort.by("price").descending()
                    ));
        } else {
            return findAll(spec,
                    PageRequest.of(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),
                            Sort.by("price").ascending()
                    ));
        }
    }
}
