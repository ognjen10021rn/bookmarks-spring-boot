package rs.raf.ostojanovic10021rn.bookmarksspringboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Controller("/user")
public class UserController {

    @ResponseStatus(OK)
    @GetMapping("/getUser")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
//        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
}
