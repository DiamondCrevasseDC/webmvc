package ind.ck.service.impl;

import ind.ck.entity.Permission;
import ind.ck.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public List<Permission> findByRoleIds(List<String> roleIds) {
        return null;
    }
}
