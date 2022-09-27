package org.example;

import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String url = "http://94.198.50.185:7081/api/users";


    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUser() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        List<User> allUser = responseEntity.getBody();
        return allUser;
    }

    public User getUser(long id) {
        ResponseEntity<User> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
                });
        return response.getBody();
    }

    public void saveUser(User user) {
//        long id = user.getId();
//        if ( id == 0) {
            ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);
            System.out.println("new User was added to server");
            System.out.println(response.getBody());
//        } else {
//            restTemplate.put(url, user);
//            System.out.println("User with id" + id + " was update");
//        }
    }
     public void editUser(User user) {
        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);
     }

    public void deleteUser(User user) {
        restTemplate.delete(url, user);

    }

}
