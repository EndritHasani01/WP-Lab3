package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public DataHolder(ArtistRepository artistRepository, SongRepository songRepository, AlbumRepository albumRepository){
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();

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


        if(albumRepository.findAll().isEmpty()){
            albums.add(new Album("album1","genre1","1980"));
            albums.add(new Album("album2","genre2","1991"));
            albums.add(new Album("album3","genre3","1982"));
            albums.add(new Album("album4","genre2","2012"));
            albums.add(new Album("album5","genre1","2022"));

            albumRepository.saveAll(albums);
        }

        if(songRepository.findAll().isEmpty()){
            songs.add(new Song("Sweet Child O' Mine", "Rock", 1987, albums.get(0)));
            songs.add(new Song("Living on a Prayer", "Rock", 1986, albums.get(1)));
            songs.add(new Song("Heroes", "Pop", 1977, albums.get(0)));
            songs.add(new Song("Bohemian Rhapsody", "Rock", 1975, albums.get(3)));
            songs.add(new Song("Rocket Man", "Rock", 1972, albums.get(3)));

            songRepository.saveAll(songs);
        }

    }

}
