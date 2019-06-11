package ind.ck.service;


import java.util.List;

public interface RoleService {

    List<String> findRoleIdByUserId(String userId);
}
