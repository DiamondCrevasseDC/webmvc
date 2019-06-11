package ind.ck.service.impl;

import ind.ck.entity.Role;
import ind.ck.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public List<String> findRoleIdByUserId(String userId) {
        return null;
    }
}
