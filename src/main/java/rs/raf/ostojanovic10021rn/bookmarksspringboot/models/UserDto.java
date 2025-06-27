package rs.raf.ostojanovic10021rn.bookmarksspringboot.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable, AuthenticationDetails {

    private Long userId;

    private String username;

    private String password;
}
