package com.congsole.SimpleSNS.service;

import com.congsole.SimpleSNS.exception.ErrorCode;
import com.congsole.SimpleSNS.exception.SnsApplicationException;
import com.congsole.SimpleSNS.model.Entity.UserEntity;
import com.congsole.SimpleSNS.model.User;
import com.congsole.SimpleSNS.repository.UserEntityRepository;
import com.congsole.SimpleSNS.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.congsole.SimpleSNS.exception.ErrorCode.DUPLICATED_USER_NAME;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    @Transactional
    public User join(String userName, String password) {
        userEntityRepository.findByUserName(userName).ifPresent(it-> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
        });
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, encoder.encode(password)));
        return User.fromEntity(userEntity);
    }

    public String login(String userName, String password) {
        // 아이디 존재 여부
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(()
                -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not found", userName)));
        // 비밀번호 일치 여부
        if(!encoder.matches(password, userEntity.getPassword())) {
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        // 토큰 생성 및 반환
        return JwtTokenUtils.generateToken(userName, secretKey, expiredTimeMs);
    }
}
