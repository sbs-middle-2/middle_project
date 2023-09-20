package com.jhg.proto.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class UserDataSet {

    private final UserService userService;

    @Autowired
    public UserDataSet(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initializeData() {
        // Insert admin user
        userService.create("admin", "admin", "admin@admin.com", "관리자", "program_manager", "0000-00-00", "KT", "010-0000-0000");

        // Insert user1
        userService.create("user1", "user1", "user1@user1.com", "user1name", "user1nickname", "1111-11-11", "KT", "010-1111-1111");
    }
}
