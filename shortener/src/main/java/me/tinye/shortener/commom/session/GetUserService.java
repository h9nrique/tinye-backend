package me.tinye.shortener.commom.session;

import me.tinye.shortener.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {

    public User execute(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        User userAuth = (User) authentication.getPrincipal();

        User user = new User();
        user.setEmail(userAuth.getEmail());

        return user;
    }

}
