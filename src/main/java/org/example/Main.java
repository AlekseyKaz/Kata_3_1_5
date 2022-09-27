package org.example;

import org.example.configuration.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String url = "http://94.198.50.185:7081/api/users/";

        User user1 = new User(3L,"James", "Brown", (byte) 12);
        User userEdit = new User(3L,"Thomas", "Shelby", (byte) 12);

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> headersE = new HttpEntity<>(headers);
        HttpEntity<User> entity1 = new HttpEntity<>(user1, headers);
        HttpEntity<User> entity2 = new HttpEntity<>(userEdit, headers);

        ResponseEntity<String> response1 = template.exchange(url, HttpMethod.GET,headersE, String.class );
        headers.add("Cookie", String.valueOf(response1.getHeaders().getFirst(HttpHeaders.SET_COOKIE)));


        String result = response1.getBody();
        System.out.println(result);

        ResponseEntity<String> response2 = template.exchange(url, HttpMethod.POST, entity1, String.class);
        System.out.print(response2.getBody());

        ResponseEntity<String> response3 = template.exchange(url, HttpMethod.PUT, entity2, String.class);
        System.out.print(response3.getBody());

        ResponseEntity<String> response4 = template.exchange(url + "3", HttpMethod.DELETE,headersE, String.class);
        System.out.println(response4.getBody());




    }
}
