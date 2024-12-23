package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String releaseYear;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    @ManyToOne
    private Genre genre;

    public Album(String name, Genre genre, String releaseYear) {
        /*this.id = (long) (Math.random()*1000);*/
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}


/*@JoinColumn(name = "album_id")*/

    /*
    @ManyToMany
    @JoinTable(
        name = "album_artist",
        joinColumns = @JoinColumn(name = "album_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;
    */
