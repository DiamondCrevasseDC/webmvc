package ind.ck.service;

import ind.ck.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findByRoleIds(List<String> roleIds);
}
