package com.congsole.SimpleSNS.service;

import com.congsole.SimpleSNS.exception.ErrorCode;
import com.congsole.SimpleSNS.exception.SnsApplicationException;
import com.congsole.SimpleSNS.model.Entity.UserEntity;
import com.congsole.SimpleSNS.model.User;
import com.congsole.SimpleSNS.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.congsole.SimpleSNS.exception.ErrorCode.DUPLICATED_USER_NAME;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;



    // TODO : implement
    public User join(String userName, String password) {
        userEntityRepository.findByUserName(userName).ifPresent(it-> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
        });
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, password));
        return User.fromEntity(userEntity);
    }

//    public String login(String userName, String password) {
//        // 아이디 존재 여부
//        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException());
//        // 비밀번호 일치 여부
//        if(userEntity.getPassword().equals(password)) {
//            throw new SnsApplicationException();
//        }
//        // 토큰 생성
//
//        return "";
//    }
}
