package huyvh.becommerce.service;

import huyvh.becommerce.dto.request.LoginRequest;
import huyvh.becommerce.dto.request.RefreshTokenRequest;
import huyvh.becommerce.dto.request.UserRequest;
import huyvh.becommerce.dto.response.AuthResponse;

import huyvh.becommerce.model.User;
import huyvh.becommerce.model.UserDetailsImpl;
import huyvh.becommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

}
