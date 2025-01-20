package com.kimulegen.forohub.domain.user;

import com.kimulegen.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegisterUserDTO registerUser(RegisterUserDTO registerUserDTO) {
        if(userRepository.existsByEmail(registerUserDTO.email())) {
            throw new IntegrityValidation("Este correo ya existe en nuestros sistemas");
        }

        if(userRepository.existsByUsername(registerUserDTO.username())) {
            throw new IntegrityValidation("Este username ya existe");
        }

        var user = new User(registerUserDTO, passwordEncoder);
        userRepository.save(user);
        return new RegisterUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public RegisterUserDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        var optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new IntegrityValidation("No existe un usuario con esa ID");
        }

        var user = optionalUser.get();

        user.updateUser(updateUserDTO);

        userRepository.save(user);

        return new RegisterUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public RegisterUserDTO deactivateUser(Long id) {
        var optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new IntegrityValidation("No existe un usuario con esa ID");
        }

        var user = optionalUser.get();
        user.deactivateUser();
        userRepository.save(user);
        return new RegisterUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }

}
