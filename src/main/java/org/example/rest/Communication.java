package org.example.rest;

import org.example.rest.entity.User;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    List<String> cookies = new ArrayList<>();

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getAllUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://94.198.50.185:7081/api/users ", String.class);
        System.out.println(cookies = forEntity.getHeaders().get("Set-Cookie"));
        return restTemplate.exchange("http://94.198.50.185:7081/api/users ", HttpMethod.GET, entity, String.class).getBody();
    }

    public String saveUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(
                        "http://94.198.50.185:7081/api/users ", HttpMethod.POST, entity, String.class)
                .getBody();

    }

    public String updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user,headers);

        return restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, entity, String.class).getBody();

    }

    public String deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "http://94.198.50.185:7081/api/users/"+id, HttpMethod.DELETE, entity, String.class).getBody();

    }

}
