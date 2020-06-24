package com.example.demo.services;

import com.example.demo.dao.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService {
    @Autowired
    HibernateTemplate hibernateTemplate;

    public User register(User user) {
        hibernateTemplate.save(user);
        System.out.println(user.getId());
        return user;
    }

    public User getByEmail(String email) {
        User example = new User();
        example.setEmail(email);
        List<User> users = hibernateTemplate.findByExample(example);
        return users.isEmpty() ? null : users.get(0);
    }
}