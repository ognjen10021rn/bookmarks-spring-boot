package rs.raf.ostojanovic10021rn.bookmarksspringboot.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
public class User implements Serializable {

    @Id
    private Long userId;

    private String username;

    private String password;

    private String email;


}
