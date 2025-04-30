package huyvh.becommerce.mapper;

import huyvh.becommerce.dto.request.UserRequest;
import huyvh.becommerce.dto.response.UserResponse;
import huyvh.becommerce.model.User;

import java.util.Locale;

public class UserMapper {
    public static User addConvertToUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .roles(request.getRoles())
                .gender(request.getGender())
                .address(request.getAddress())
                .phone(request.getPhone())
                .refreshToken(request.getRefreshToken())
                .build();
        return user;
    }

    public static User updateConvertToUser(UserRequest request, Long id) {
        User user = User.builder()
                .id(id)
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .roles(request.getRoles())
                .gender(request.getGender())
                .address(request.getAddress())
                .phone(request.getPhone())
                .refreshToken(request.getRefreshToken())
                .build();
        return user;
    }

    public static UserResponse convertToUserResponse(User user) {
        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .gender(user.getGender())
                .address(user.getAddress())
                .phone(user.getPhone())
                .refreshToken(user.getRefreshToken())
                .build();
        return response;
    }

}
