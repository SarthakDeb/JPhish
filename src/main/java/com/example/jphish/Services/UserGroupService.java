package com.example.jphish.Services;

import com.example.jphish.Models.UserGroup;
import org.springframework.web.multipart.MultipartFile;

public interface UserGroupService {
    public UserGroup createGroupWithUsers(String groupName, MultipartFile file) throws Exception;
}
