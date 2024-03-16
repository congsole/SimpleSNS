package com.congsole.SimpleSNS.controller;

import com.congsole.SimpleSNS.controller.request.UserJoinRequest;
import com.congsole.SimpleSNS.controller.response.Response;
import com.congsole.SimpleSNS.controller.response.UserJoinResponse;
import com.congsole.SimpleSNS.model.User;
import com.congsole.SimpleSNS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // TODO : Implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        User user = userService.join(request.getUserName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

    @GetMapping("/login")
    public void login() {

    }
}
