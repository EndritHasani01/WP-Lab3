package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    /*
    @ManyToMany(mappedBy = "artists")  // Inverse side (mappedBy tells JPA it's not the owning side)
    private List<Album> albums;
    */

    public Artist(String firstName, String lastName, String bio) {
        /*this.id = (long) (Math.random()*1000);*/
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

}
