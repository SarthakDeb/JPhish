package com.example.jphish.Services;

import com.example.jphish.Models.User;
import com.example.jphish.Models.UserGroup;
import com.example.jphish.Repositories.UserGroupRepository;
import com.example.jphish.Repositories.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private UserGroupRepository userGroupRepository;
    private UserRepository userRepository;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserRepository userRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
    }
    @Override
    public UserGroup createGroupWithUsers(String groupName, MultipartFile file) throws Exception {
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName(groupName);
        userGroup.setCreatedAt(LocalDateTime.now());

        List<User> users = parseUsersFromCSV(file);

        for (User user : users) {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isEmpty()) {
                userGroup.getUsers().add(user);
            }
        }

        userGroupRepository.save(userGroup);

        return userGroup;
    }

    private List<User> parseUsersFromCSV(MultipartFile file) throws IOException {
        List<User> users = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader,
                    CSVFormat.DEFAULT.withHeader("Name", "Email").withSkipHeaderRecord());

            for (CSVRecord record : csvParser) {
                String name = record.get("Name");
                String email = record.get("Email");

                User user = new User();
                user.setName(name);
                user.setEmail(email);

                users.add(user);
            }
        }
        return users;
    }
}

