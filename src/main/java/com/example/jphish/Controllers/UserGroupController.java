package com.example.jphish.Controllers;

import com.example.jphish.Models.UserGroup;
import com.example.jphish.Services.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usergroup")
public class UserGroupController {

    private  UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserGroup>createUserGroup(@RequestPart("Groupname") String userGroupName,
                                                    @RequestPart("file") MultipartFile file) throws Exception {

        UserGroup newUserGroup = userGroupService.createGroupWithUsers(userGroupName, file);

        return new ResponseEntity<>(newUserGroup, HttpStatus.OK);
    }
}
