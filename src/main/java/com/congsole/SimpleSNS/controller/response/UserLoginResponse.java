package com.congsole.SimpleSNS.controller.response;

import com.congsole.SimpleSNS.model.User;
import com.congsole.SimpleSNS.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {
    private String token;

}
