package local.heftyb.howto.controllers;

import local.heftyb.howto.models.Post;
import local.heftyb.howto.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController
{

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts",
        produces = {"application/json"})
    public ResponseEntity<?> listAllPost()
    {
        List<Post> posts = postService.findAll();
        return new ResponseEntity<>(posts,
            HttpStatus.OK);
    }


}
