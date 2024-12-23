package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.model.enumerations.Role;
import mk.ukim.finki.wp.lab.repository.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public DataHolder(ArtistRepository artistRepository, SongRepository songRepository, AlbumRepository albumRepository, GenreRepository genreRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();
    public static List<Genre> genres = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    @PostConstruct
    public void init(){
        if(artistRepository.findAll().isEmpty()){
            artists.add(new Artist( "Axl", "Rose", "Lead vocalist of Guns N' Roses"));
            artists.add(new Artist( "Jon", "Bon Jovi", "Lead singer of Bon Jovi"));
            artists.add(new Artist( "David", "Bowie", "Iconic singer-songwriter"));
            artists.add(new Artist( "Freddie", "Mercury", "Legendary frontman of Queen"));
            artists.add(new Artist( "Elton", "John", "Famous pop and rock singer"));

            artistRepository.saveAll(artists);
        }

        if(genreRepository.findAll().isEmpty()){
            genres.add(new Genre("genre1"));
            genres.add(new Genre("genre2"));
            genres.add(new Genre("genre3"));
            genres.add(new Genre("genre4"));

            genreRepository.saveAll(genres);
        }

        if(albumRepository.findAll().isEmpty()){
            albums.add(new Album("album1", genres.get(0),"1980"));
            albums.add(new Album("album2", genres.get(2),"1991"));
            albums.add(new Album("album3",genres.get(3),"1982"));
            albums.add(new Album("album4",genres.get(1),"2012"));
            albums.add(new Album("album5",genres.get(1),"2022"));

            albumRepository.saveAll(albums);
        }

        if(songRepository.findAll().isEmpty()){
            songs.add(new Song("Sweet Child O' Mine", genres.get(2), 1987, albums.get(0)));
            songs.add(new Song("Living on a Prayer", genres.get(0), 1986, albums.get(1)));
            songs.add(new Song("Heroes", genres.get(3), 1977, albums.get(0)));
            songs.add(new Song("Bohemian Rhapsody", genres.get(3), 1975, albums.get(3)));
            songs.add(new Song("Rocket Man", genres.get(1), 1972, albums.get(3)));

            songRepository.saveAll(songs);
        }

        if(userRepository.count() == 0){
            users.add(new User("John", passwordEncoder.encode("user"), "John", "John", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            userRepository.saveAll(users);
        }

    }

}
