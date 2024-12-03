package org.example.ebankifysecurity.service.Auth;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.example.ebankifysecurity.config.JwtTokenProvider;
import org.example.ebankifysecurity.dto.LoginRequestDTO;
import org.example.ebankifysecurity.dto.LoginResponseDTO;
import org.example.ebankifysecurity.repository.UserRepository;
import org.example.ebankifysecurity.entity.User;
import org.example.ebankifysecurity.mapper.UserMapper;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository userRepository, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(loginRequest.getEmail());

        String jwt = tokenProvider.generateToken(user);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(jwt);
        response.setUser(userMapper.toDTO(user));
        return response;
    }
}