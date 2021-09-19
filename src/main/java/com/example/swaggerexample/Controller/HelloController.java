package com.example.swaggerexample.Controller;

import com.example.swaggerexample.Useri;
import com.example.swaggerexample.UserRepository;
import com.example.swaggerexample.Useridto;
import com.example.swaggerexample.Userservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final UserRepository userRepository;
    private final Userservice userservice;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    @Operation(summary = "test hello", description = "hello api example")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK !!"),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
//    })
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@Parameter(description = "이름", required = true, example = "Park") @RequestParam String name) {
        Useridto useri = new Useridto();
        useri.setId("1"); useri.setEmail("tom"); useri.setName("tom");
        Useri seri = new Useri();
        seri.setId(useri.getId());
        seri.setEmail(useri.getEmail());
        seri.setName(useri.getName());
        userRepository.save(seri);
        return ResponseEntity.ok("hello " + name);
    }
    @PostMapping("/test")
    public void test(@RequestBody Useri user) throws IOException {
        System.out.println(user);
        userservice.save(user);
    }
    @CacheEvict(key = "#id", value = "echo")
//    @Cacheable(key="#id",value = "echo")
    @GetMapping("/echo/{id}")
    public ResponseEntity<Optional<Useri>> echo (@PathVariable String id){
        return ResponseEntity.ok(userservice.echos(id));
    }
    @GetMapping("/redistest")
    public ResponseEntity<?> redis(){
        ValueOperations<String,Object> vop = redisTemplate.opsForValue();
        vop.set("hello","monkey");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/redisTest/{key}")
    public ResponseEntity<?> getRedisKey(@PathVariable String key) {
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        Object value = vop.get(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

}