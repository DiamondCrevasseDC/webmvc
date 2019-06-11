package ind.ck.service;

import ind.ck.entity.User;

public interface UserService {

    User findByToken(String token);
}
