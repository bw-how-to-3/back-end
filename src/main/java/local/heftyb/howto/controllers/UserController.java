package local.heftyb.howto.controllers;

import local.heftyb.howto.models.User;
import local.heftyb.howto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users",
        produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> list = userService.findAll();
        return new ResponseEntity<>(list,
            HttpStatus.OK);
    }

    @GetMapping(value = "/user/username/{username}",
        produces = {"application/json"})
    public ResponseEntity<?> getuserByUsername(
        @PathVariable
            String username)
    {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user,
            HttpStatus.OK);
    }

    @GetMapping(value = "/user/username/like/{username}",
        produces = {"application/json"})
    public ResponseEntity<?> getUsersLike(
        @PathVariable
            String username)
    {
        List<User> users = userService.findByNameContaining(username);
        return new ResponseEntity<>(users,
            HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
