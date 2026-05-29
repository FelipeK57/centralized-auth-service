package com.semcon.sgu.modules.auth.services;

import com.semcon.sgu.modules.auth.dtos.LoginRequestDto;
import com.semcon.sgu.modules.auth.dtos.LoginResponseDto;
import com.semcon.sgu.modules.auth.exceptions.ForbiddenRoleException;
import com.semcon.sgu.modules.auth.exceptions.InvalidCredentialsException;
import com.semcon.sgu.modules.users.entity.User;
import com.semcon.sgu.modules.users.repository.UserRepository;
import com.semcon.sgu.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> Objects.equals(auth.getAuthority(), "ROLE_admin"));

        if (!isAdmin) {
            throw new ForbiddenRoleException();
        }

        User user = userRepository.findByEmail(userDetails.getUsername());
        Map<String, Object> claims = Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "lastName", user.getLastName(),
                "role", user.getRole(),
                "workArea", user.getWorkArea().getName()
        );

        return new LoginResponseDto(jwtService.generateToken(userDetails, claims));
    }
}
