package huyvh.becommerce.controller;

import huyvh.becommerce.dto.request.CategoryRequest;
import huyvh.becommerce.dto.response.ErrorResponse;
import huyvh.becommerce.dto.response.ResponseData;
import huyvh.becommerce.model.Category;
import huyvh.becommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CategoryRequest request) {
        try {
            Category responseDTO = categoryService.add(request);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "save category successfully!",
                    responseDTO, null, null, null, null));
        } catch (Exception e) {
            String stackTraceString = Arrays.toString(e.getStackTrace());
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage(),
                            LocalDateTime.now(),
                            stackTraceString // hoặc có thể dùng stackTrace
                    )
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryRequest request, @PathVariable Long id) {
        try {
            Category responseDTO = categoryService.update(request, id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "update category successfully!",
                    responseDTO, null, null, null, null));
        } catch (Exception e) {
            String stackTraceString = Arrays.toString(e.getStackTrace());
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage(),
                            LocalDateTime.now(),
                            stackTraceString // hoặc có thể dùng stackTrace
                    )
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try {
            List<Category> responseDTO = categoryService.findAll();
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find all category successfully!",
                    responseDTO, null, null, null, null));
        } catch (Exception e) {
            String stackTraceString = Arrays.toString(e.getStackTrace());
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage(),
                            LocalDateTime.now(),
                            stackTraceString // hoặc có thể dùng stackTrace
                    )
            );
        }
    }
}
