package huyvh.becommerce.controller;

import huyvh.becommerce.dto.request.OrderRequest;
import huyvh.becommerce.dto.response.ErrorResponse;
import huyvh.becommerce.dto.response.OrderResponse;
import huyvh.becommerce.dto.response.ResponseData;
import huyvh.becommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody OrderRequest request) {
        try {
            OrderResponse responseDTO = orderService.add(request);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "save Order successfully!",
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
            Page<OrderResponse> responseDTO = orderService.findAll(page, size);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "find all Order successfully!",
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
