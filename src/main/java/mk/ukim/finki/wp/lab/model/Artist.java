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

    /*
    * @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    * private List<Song> songs = new ArrayList<>();
     */

    public Artist(String firstName, String lastName, String bio) {
        /*this.id = (long) (Math.random()*1000);*/
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

}

/*
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artwork> artworks = new ArrayList<>();

    // getters and setters

    public void addArtwork(Artwork artwork) {
        this.artworks.add(artwork);
        artwork.setArtist(this);  // Ensure both sides are synchronized
    }
}

@Entity
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    // getters and setters

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

*/
