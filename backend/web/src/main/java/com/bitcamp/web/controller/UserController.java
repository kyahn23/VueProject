package com.bitcamp.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bitcamp.web.common.lambda.IFunction;
import com.bitcamp.web.common.lambda.ISupplier;
import com.bitcamp.web.domain.UserDTO;
import com.bitcamp.web.entities.User;
import com.bitcamp.web.repositories.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserDTO user;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository repo;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // 회원가입
    @PostMapping("")
    public void signup(@RequestBody UserDTO dto) {
        // HashMap<String, String> map = new HashMap<>();
        User entity = new User();
        entity.setEmailId(dto.getEmailId());
        entity.setPassword(dto.getPassword());
        entity.setUserName(dto.getUserName());

        repo.save(entity);
        // map.put("result", "Success");
    }

    // 로그인
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO dto) {
        ISupplier fx = (()-> {
            return repo.findUserByEmailIdAndPassword(dto.getEmailId(), dto.getPassword());
    });
        return modelMapper.map(fx.get(), UserDTO.class);
    }

    // 회원 리스트
    @GetMapping("/userlist")
    public Iterable<UserDTO> userlist() {
        System.out.println("== list들어옴 ==");
        Iterable<User> entities = repo.findAll();
        List<UserDTO> list = new ArrayList<>();
        for (User u : entities) {
            UserDTO user = modelMapper.map(u, UserDTO.class);
            list.add(user);
        }
        return list;
    }

    // 마이페이지
    @GetMapping("/mypage/{id}")
    public UserDTO mypage(@PathVariable String id){
        repo.findUserByEmailId(id);
        return modelMapper.map(repo.findUserByEmailId(id), UserDTO.class);
    }

    // 비밀번호 수정
    @PutMapping("/modi")
    public void modiPass(@RequestBody UserDTO dto){
        System.out.println("수정들어옴");
        User entity = new User();
        entity = repo.findUserByEmailId(dto.getEmailId());
        entity.setPassword(dto.getPassword());

        repo.save(entity);
    }

    // 탈퇴
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        User entity = new User();
        entity = repo.findUserByEmailId(id);

        repo.deleteById(entity.getUno());
    }
}