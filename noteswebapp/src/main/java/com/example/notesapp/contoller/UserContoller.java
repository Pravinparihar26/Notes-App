package com.example.notesapp.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.notesapp.service.UserService;
import com.example.notesapp.DTO.ResponseDTO;
import com.example.notesapp.DTO.UserDTO;

@RestController
@RequestMapping("/account")
class UserController{

    @Autowired
    private UserService user;

    @PostMapping("/register")
    public ResponseDTO userRegister(@RequestBody UserDTO userdto){
        return user.register(userdto.getUserName(),userdto.getPassword());
    }

    @PostMapping("/login")
    public ResponseDTO userLogin(@RequestBody UserDTO userdto){
        return user.login(userdto.getUserName(),userdto.getPassword());
    }
}