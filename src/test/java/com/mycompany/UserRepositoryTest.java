package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;
import java.util.function.BooleanSupplier;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("alex.miwhai@gmail.com");
        user.setPassword("alexw1234");
        user.setFirstName("Andrwei");
        user.setLastName("Mihaiw");

        User savedUser = repo.save(user);

       // Assertions.assertTrue(savedUser.getId() > 0, "Id should be grater that 0");


    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertNotNull(users);

        users.forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user =optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);

        User updateUser = repo.findById(userId).get();
        Assertions.assertTrue(updateUser.getPassword() == "hello2000");
    }

    @Test
    public void testGet(){
        Integer userId = 8;
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 8;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertTrue(!(optionalUser.isPresent()));
    }

    @Test
    public void testDeleteAll(){
        repo.deleteAll();

    }
}
