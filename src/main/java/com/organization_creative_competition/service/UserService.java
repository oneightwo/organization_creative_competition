package com.organization_creative_competition.service;

import com.organization_creative_competition.model.User;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

    List<User> getUsersByCreationUserId(Long id);
}
