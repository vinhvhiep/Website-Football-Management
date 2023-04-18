package com.example.doannmcnpm.controller;



import com.example.doannmcnpm.dto.ResponseDto;
import com.example.doannmcnpm.dto.user.SignInDto;
import com.example.doannmcnpm.dto.user.SignInReponseDto;
import com.example.doannmcnpm.dto.user.SignupDto;
import com.example.doannmcnpm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    // two apis

    // signup

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    // signin

    @PostMapping("/signin")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }


}