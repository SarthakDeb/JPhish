package com.example.jphish.Services;

import com.example.jphish.Dtos.CreateUserDto;
import com.example.jphish.Exceptions.UserExistsException;
import com.example.jphish.Exceptions.UserNotFound;
import com.example.jphish.Models.User;
import com.example.jphish.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(CreateUserDto createUserDto) throws UserExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(createUserDto.getEmail());
        if(!optionalUser.isEmpty()){
            throw new UserExistsException ("User with "+createUserDto.getEmail()+" already exists");
        }
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFound {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound("User not found with email: " + email));
    }

    @Override
    public List<User> findUserByName(String name) {
        List<User> user = userRepository.findByName(name);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
