package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.Privilege;
import edu.mum.ea.socialmedia.model.Role;
import edu.mum.ea.socialmedia.service.RoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@Getter
@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public Role add(@RequestBody Role role){
        return getRoleService().add(role);
    }

    @GetMapping("/find/{id}")
    public Role getById(@PathVariable("id") Long id){
        return getRoleService().findById(id);
    }

    @GetMapping("/find/{name}")
    public Role getByName(@PathVariable("name") String name){
        return getRoleService().findByName(name);
    }

    @GetMapping("find")
    public List<Role> findAll(){
        return getRoleService().findAll();
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        getRoleService().deleteById(id);
        return true;
    }
}
