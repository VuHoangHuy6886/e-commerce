package huyvh.becommerce.service;

import huyvh.becommerce.dto.request.CategoryRequest;
import huyvh.becommerce.model.Category;
import huyvh.becommerce.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public Category add(CategoryRequest request) {
        Category category = Category.builder().name(request.getName()).build();
        return categoryRepo.save(category);
    }

    public Category update(CategoryRequest request, Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category id not found"));
        category.setName(request.getName());
        return categoryRepo.save(category);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
}
