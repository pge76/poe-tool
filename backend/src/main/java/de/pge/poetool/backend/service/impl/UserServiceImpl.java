package de.pge.poetool.backend.service.impl;

import de.pge.poetool.backend.entity.dto.UserDto;
import de.pge.poetool.backend.entity.mapper.UserMapper;
import de.pge.poetool.backend.errors.UserNotFoundException;
import de.pge.poetool.backend.repository.UserRepository;
import de.pge.poetool.backend.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDto> getUser(String email) {
        return userRepository.findUserByEmail(email)
                .map(UserMapper.INSTANCE::toDTO)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));

    }

    @Override
    public Flux<UserDto> all() {
        return userRepository.findAll()
                .map(UserMapper.INSTANCE::toDTO)
                .switchIfEmpty(Flux.error(UserNotFoundException::new));
    }
}
