package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.cache.CacheLoaderService;
import com.braquec.rmetaphoto.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersServiceImpl implements UsersService, CacheLoaderService<Long, User> {
    private static final String USERS_BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public User getApiData(Long key) {
        return getById(key);
    }

    @Override
    public User getById(Long id) {
        StringBuilder url = new StringBuilder(USERS_BASE_URL);
        url.append("/").append(id);

        return restTemplate.getForObject(url.toString(), User.class);
    }
}
