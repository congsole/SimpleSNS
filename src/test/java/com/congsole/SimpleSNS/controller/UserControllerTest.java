//package com.congsole.SimpleSNS.controller;
//
//import com.congsole.SimpleSNS.controller.request.UserJoinRequest;
//import com.congsole.SimpleSNS.controller.request.UserLoginRequest;
//import com.congsole.SimpleSNS.exception.ErrorCode;
//import com.congsole.SimpleSNS.exception.SnsApplicationException;
//import com.congsole.SimpleSNS.model.User;
//import com.congsole.SimpleSNS.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//    @MockBean
//    private UserService userService;
//    @Test
//    public void 회원가입_성공() throws Exception {
//        String userName = "solhe";
//        String password = "congsole";
//
//        // mocking
//        when(userService.join(userName, password))
//                .thenReturn(mock(User.class));
//
//        mockMvc.perform(post("/api/v1/users/join")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))) //Add Request Body
//        ).andDo(print())
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void 회원가입_실패_이미가입된아이디() throws Exception {
//        String userName = "solhe";
//        String password = "congsole";
//
//        // mocking
//        when(userService.join(userName, password))
//                .thenThrow(new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));
//
//
//        mockMvc.perform(post("/api/v1/users/join")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))) //Add Request Body
//                ).andDo(print())
//                .andExpect(status().isConflict());
//    }
//    @Test
//    public void 로그인_성공() throws Exception {
//        String userName = "solhe";
//        String password = "congsole";
//
//        // mocking
//        when(userService.login(userName, password))
//                .thenReturn(mock("test_token"));
//
//        mockMvc.perform(post("/api/v1/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))) //Add Request Body
//                ).andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 로그인_실패_존재하지않는아이디() throws Exception {
//        String userName = "solhe";
//        String password = "congsole";
//
//        // mocking
//        when(userService.login(userName, password))
//                .thenThrow(mock(new SnsApplicationException()));
//
//        mockMvc.perform(post("/api/v1/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))) //Add Request Body
//                ).andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void 로그인_실패_틀린패스워드() throws Exception {
//        String userName = "solhe";
//        String password = "congsole";
//
//        // mocking
//        when(userService.login(userName, password))
//                .thenThrow(mock(new SnsApplicationException()));
//
//        mockMvc.perform(post("/api/v1/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))) //Add Request Body
//                ).andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//}