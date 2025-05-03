package huyvh.becommerce.controller;

import huyvh.becommerce.service.CloudinaryService;
import huyvh.becommerce.utils.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UploadImageController {
    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam(value = "folder", defaultValue = "uploads") String folder) {
        try {
            FileValidator.validateImageFile(file);
            String imageUrl = cloudinaryService.uploadFile(file, folder);
            return ResponseEntity.ok().body(Map.of(
                    "status", 200,
                    "message", "Upload successful",
                    "url", imageUrl
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload file: " + e.getMessage());
        }
    }
}
