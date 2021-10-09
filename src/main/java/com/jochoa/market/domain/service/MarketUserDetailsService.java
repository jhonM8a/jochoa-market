package com.jochoa.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarketUserDetailsService implements UserDetailsService {

    private static List<User> users = new ArrayList();

    public MarketUserDetailsService(){
        users.add(new User("jochoa","{noop}jochoa", new ArrayList<>()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.stream().filter(userList -> userList.getUsername().equals(username)).findAny();

        if (!user.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }


}
