package rs.raf.ostojanovic10021rn.bookmarksspringboot.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.raf.ostojanovic10021rn.bookmarksspringboot.models.User;
import rs.raf.ostojanovic10021rn.bookmarksspringboot.repositories.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with the username: " + username + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

    }

//    public UserDto getUserByUsername(String username) {
//
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return null;
//        }
//        UserDto userDto = new UserDto();
//        userDto.setUserId(user.getUserId());
//        userDto.setUsername(user.getUsername());
//        userDto.setPassword(user.getPassword());
//        return userDto;
//    }

}
