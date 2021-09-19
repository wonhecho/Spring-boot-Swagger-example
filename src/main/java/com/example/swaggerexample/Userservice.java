package com.example.swaggerexample;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Userservice {
    private final UserRepository userRepository;

    public void save(Useri useri) throws IOException {
        System.out.println("==========================================" + useri);
        userRepository.save(useri);
    }
    @Cacheable(key="#echo",value = "echos")
    public Optional<Useri> echos(String echo){
        return userRepository.findById(echo);
    }
}
