package edu.mum.ea.socialmedia.controller;

import edu.mum.ea.socialmedia.model.Privilege;
import edu.mum.ea.socialmedia.service.PrivilegeService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@Getter
@RestController
@RequestMapping(("/privilege"))
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping("/add")
    public Privilege add(@RequestBody Privilege privilege){
        return getPrivilegeService().add(privilege);
    }

    @GetMapping("/find/{id}")
    public Privilege get(@PathVariable("id") Long id){
        return getPrivilegeService().findById(id).get();
    }

    @GetMapping("find")
    public List<Privilege> findAll(){
        return getPrivilegeService().findAll();
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        getPrivilegeService().delete(id);
        return true;
    }
}
