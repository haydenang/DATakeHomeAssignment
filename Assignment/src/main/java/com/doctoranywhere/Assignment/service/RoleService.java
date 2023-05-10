package com.doctoranywhere.Assignment.service;


import com.doctoranywhere.Assignment.model.Role;
import com.doctoranywhere.Assignment.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    private RoleRepo roleRepo;
    @Autowired
    public RoleService(RoleRepo roleRepo){
        this.roleRepo = roleRepo;
    }

    public Optional<Role> getRoleById(long id){
        return roleRepo.findById(id);
    }

    public List<Role> getAllRole(){
        return roleRepo.findAll();
    }

    public Role uploadRole(Role user){
        return roleRepo.saveAndFlush(user);
    }

    public Role updateRole(long id, Role role){
        Optional<Role> retrievedUser = roleRepo.findById(id);
        if(retrievedUser.isPresent()){
            Role toBeUpdatedRole = retrievedUser.get();
            toBeUpdatedRole.setName(role.getName());
            toBeUpdatedRole.setUsers(role.getUsers());
            return roleRepo.save(toBeUpdatedRole);
        }
        else{
            return uploadRole(role);
        }
    }

    public void deleteTask(long id){
        roleRepo.deleteById(id);
    }
}
