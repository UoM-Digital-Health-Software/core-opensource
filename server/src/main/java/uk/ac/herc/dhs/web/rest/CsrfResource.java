package uk.ac.herc.dhs.web.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CsrfResource {


    @GetMapping("/csrf")
    public String getCsrfToken() {
        return "granted!";
    }
}
