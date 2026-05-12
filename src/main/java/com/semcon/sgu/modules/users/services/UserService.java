package com.semcon.sgu.modules.users.services;

import com.semcon.sgu.modules.users.dtos.CreateUserDto;
import com.semcon.sgu.modules.users.dtos.UserDto;
import com.semcon.sgu.modules.users.entity.User;
import com.semcon.sgu.modules.users.mappers.UserMapper;
import com.semcon.sgu.modules.users.repository.UserRepository;
import com.semcon.sgu.modules.workareas.entity.WorkArea;
import com.semcon.sgu.modules.workareas.repository.WorkAreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WorkAreaRepository workAreaRepository;
    private final UserMapper userMapper;

    public List<UserDto> getUsersList() {
        List<User> users = this.userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User existingUser = userRepository.findByEmail(createUserDto.email());
        if (existingUser == null) {
            WorkArea workArea = workAreaRepository.findByName(createUserDto.workArea());
            User user = User.builder()
                    .name(createUserDto.name())
                    .lastName(createUserDto.lastName())
                    .documentType(createUserDto.documentType())
                    .documentNumber(createUserDto.documentNumber())
                    .role(createUserDto.role())
                    .email(createUserDto.email())
                    .password(createUserDto.documentNumber())
                    .active(true)
                    .workArea(workArea)
                    .build();
            userRepository.save(user);
            return userMapper.toDto(user);
        } else {
            return null;
        }
    }
}
