package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "genre")
    List<Song> songs;

    @OneToMany(mappedBy = "genre")
    List<Album> albums;

    public Genre(String name) {
        /*this.trackId = (long) (Math.random()*1000);*/
        this.name = name;
    }

}
