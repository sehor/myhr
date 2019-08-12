package myhr.service;

import myhr.data.domain.user.Menu;
import myhr.data.domain.user.Role;
import myhr.data.repository.MenuRepository;
import myhr.data.repository.MenuRoleRepository;
import myhr.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuRoleRepository mrRepository;





    public String[] findRolesNameByPath(String path){

        List<String> roleNames=new ArrayList<>();
        for(Menu menu:menuRepository.findAll()){
            if(antPathMatcher.match(menu.getPattern(),path)) {
                 for(Role role: roleRepository.findByMid(menu.getId())){
                     roleNames.add(role.getName());
                 }
            }
        }
        return  roleNames.toArray(new String[roleNames.size()]);
    }
}
