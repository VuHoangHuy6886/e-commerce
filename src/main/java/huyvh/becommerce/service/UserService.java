package huyvh.becommerce.service;

import huyvh.becommerce.dto.request.LoginRequest;
import huyvh.becommerce.dto.request.RefreshTokenRequest;
import huyvh.becommerce.dto.request.UserRequest;
import huyvh.becommerce.dto.response.AuthResponse;
import huyvh.becommerce.dto.response.UserResponse;
import huyvh.becommerce.mapper.UserMapper;
import huyvh.becommerce.model.User;
import huyvh.becommerce.model.UserDetailsImpl;
import huyvh.becommerce.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .gender(true)
                .address(request.getAddress())
                .roles("USER")
                .build();

        userRepository.save(user);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        var accessToken = jwtService.generateAccessToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message("User registered successfully")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        var accessToken = jwtService.generateAccessToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message("Login successful")
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // Validate refresh token
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new RuntimeException("Refresh token is invalid");
        }

        // Lấy thông tin user từ refresh token
        String userEmail = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra refresh token có khớp với database
        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new RuntimeException("Refresh token does not match");
        }
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        // Tạo token mới
        String newAccessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        // Cập nhật refresh token mới vào database
        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .message("Token refreshed successfully")
                .build();
    }

    public UserResponse update(UserRequest request, Long id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        User user = UserMapper.updateConvertToUser(request, id);
        return UserMapper.convertToUserResponse(userRepository.save(user));
    }

    public Page<UserResponse> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageRequest);
        Page<UserResponse> responses = users.map(UserMapper::convertToUserResponse);
        return responses;
    }
}
