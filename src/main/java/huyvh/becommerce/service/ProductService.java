package huyvh.becommerce.service;

import huyvh.becommerce.dto.request.ProductRequest;
import huyvh.becommerce.dto.response.ProductResponse;
import huyvh.becommerce.mapper.ProductMapper;
import huyvh.becommerce.model.Category;
import huyvh.becommerce.model.Product;
import huyvh.becommerce.repo.CategoryRepo;
import huyvh.becommerce.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public ProductResponse add(ProductRequest request) {
        Category category = categoryRepo.findById(Long.parseLong(request.getCategory())).orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = ProductMapper.addConvertToProduct(request, category);
        return ProductMapper.ConvertToProductResponse(productRepo.save(product));
    }

    public ProductResponse update(ProductRequest request, Long id) {
        Category category = categoryRepo.findById(Long.parseLong(request.getCategory())).orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = ProductMapper.updateConvertToProduct(request, category, id);
        return ProductMapper.ConvertToProductResponse(productRepo.save(product));
    }

    public Page<ProductResponse> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepo.findAll(pageRequest).map(ProductMapper::ConvertToProductResponse);
    }

    public Page<ProductResponse> searchProducts(String name, Double minPrice, Double maxPrice,
                                                String status, Long categoryId, String sortDirection, Pageable pageable) {
        Page<Product> products = productRepo.searchProducts(name, minPrice, maxPrice, status, categoryId, sortDirection, pageable);
        Page<ProductResponse> responses = products.map(ProductMapper::ConvertToProductResponse);
        return responses;
    }
}
