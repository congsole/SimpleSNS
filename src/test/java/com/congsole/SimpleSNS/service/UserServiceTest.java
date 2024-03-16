package com.congsole.SimpleSNS.service;

import com.congsole.SimpleSNS.exception.SnsApplicationException;
import com.congsole.SimpleSNS.fixture.UserEntityFixture;
import com.congsole.SimpleSNS.model.Entity.UserEntity;
import com.congsole.SimpleSNS.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserEntityRepository userEntityRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;


    @Test
    void 회원가입_통과() {
        String userName = "sole";
        String password = "congsole";

        // mocking
        when(userEntityRepository.findByUserName(userName))
                .thenReturn(Optional.empty());
//        when(encoder.encode(password)).thenReturn("encoded_password");
        when(userEntityRepository.save(any()))
                .thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

        Assertions.assertDoesNotThrow(() -> userService.join(userName, password));
    }
    @Test
    void 회원가입_불가_중복된아이디() {
        String userName = "sole";
        String password = "congsole";

        // mocking
        when(userEntityRepository.findByUserName(userName))
                .thenReturn(Optional.of(UserEntityFixture.get(userName, password)));
//        when(userEntityRepository.save(any()))
//                .thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

        Assertions.assertThrows(SnsApplicationException.class, () -> userService.join(userName, password));
    }

//    @Test
//    void 로그인_통과() {
//        String userName = "solhe";
//        String password = "congsole";
//
//        UserEntity fixture = UserEntityFixture.get(userName, password);
//        // mocking
//        when(userEntityRepository.findByUserName(userName))
//                .thenReturn(Optional.of(fixture));
//        Assertions.assertDoesNotThrow(() -> userService.login(userName, password));
//    }
//
//    @Test
//    void 로그인_불가_존재하지않는아이디() {
//        String userName = "solhe";
//        String password = "congsole";
//
//        // mocking
//        when(userEntityRepository.findByUserName(userName))
//                .thenReturn(Optional.empty());
//        Assertions.assertThrows(SnsApplicationException.class, () -> userService.login(userName, password));
//    }
//
//    @Test
//    void 로그인_불가_비번틀린경우() {
//        String userName = "solhe";
//        String password = "congsole";
//        String wrongPassword = "wrongPassword";
//        UserEntity fixture = UserEntityFixture.get(userName, password);
//
//        // mocking
//        when(userEntityRepository.findByUserName(userName))
//                .thenReturn(Optional.of(fixture));
//        Assertions.assertThrows(SnsApplicationException.class, () -> userService.login(userName, wrongPassword));
//    }
}