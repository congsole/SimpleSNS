package com.congsole.SimpleSNS.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequest {
    private String userName;
    private String password;
}
