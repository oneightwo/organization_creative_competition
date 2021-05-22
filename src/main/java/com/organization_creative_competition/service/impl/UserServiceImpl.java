package com.organization_creative_competition.service.impl;

import com.organization_creative_competition.constant.Role;
import com.organization_creative_competition.model.User;
import com.organization_creative_competition.repository.UserRepository;
import com.organization_creative_competition.security.SecurityUtils;
import com.organization_creative_competition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("User id is null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id=" + id + " not found"));
    }

    @Override
    public User save(User user) {
        if (Objects.isNull(user.getRole())) {
            user.setRole(Role.USER);
        }

        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.nonNull(userFromContext)) {
            user.setCreatedBy(new User().setId(getById(userFromContext.getId()).getId()));
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        getById(user.getId());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByCreationUserId(Long id) {
        return userRepository.findByCreatedById(id);
    }
}
