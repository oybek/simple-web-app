package com.oybek.webapp.service.interfaces;

import com.oybek.webapp.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByLogin(String login);
}
