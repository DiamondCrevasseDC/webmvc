package ind.ck.service.impl;

import ind.ck.entity.User;
import ind.ck.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByToken(String token) {
        return null;
    }
}
