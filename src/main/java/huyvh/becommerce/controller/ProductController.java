package huyvh.becommerce.controller;

import huyvh.becommerce.dto.request.ProductRequest;
import huyvh.becommerce.dto.response.ErrorResponse;
import huyvh.becommerce.dto.response.ProductResponse;
import huyvh.becommerce.dto.response.ResponseData;
import huyvh.becommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody ProductRequest request) {
        try {
            ProductResponse responseDTO = productService.add(request);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "save Product successfully!",
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
    public ResponseEntity<?> update(@RequestBody ProductRequest request, @PathVariable Long id) {
        try {
            ProductResponse responseDTO = productService.update(request, id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "update Product successfully!",
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
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        try {
            Page<ProductResponse> responseDTO = productService.findAll(page, size);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find all products successfully!",
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

    @GetMapping("/findBy")
    public ResponseEntity<?> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection,
            @PageableDefault(size = 10, sort = "price",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        try {
            Page<ProductResponse> responseDTO = productService.searchProducts(name, minPrice, maxPrice, status, categoryId, sortDirection, pageable);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find products successfully!",
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
