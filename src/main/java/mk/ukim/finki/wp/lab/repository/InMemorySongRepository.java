/*
package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemorySongRepository {
    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Song findByTrackId(Long id){
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(id)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        List<Artist> performers = song.getPerformers();
        if(performers.stream().noneMatch(p -> Objects.equals(p.getId(), artist.getId()))){
            performers.add(artist);
        }
        return artist;
    }

    public Optional<Song> save(String title, String genre, Integer releaseYear, Album album){
        Song song = new Song(title, genre, releaseYear, album);
        DataHolder.songs.removeIf(s -> Objects.equals(s.getTitle(), title));
        DataHolder.songs.add(song);
        return Optional.of(song);
    }

    public void deleteById(Long id) {
        DataHolder.songs.removeIf(s -> s.getTrackId().equals(id));
    }

*/
/*    public List<Song> filter(String fl){
        return DataHolder.songs.stream().filter(e -> fl == null || e.getTitle().contains(fl) (&& e.getGenre().equals("Pop"))).collect(Collectors.toList());
    }*//*

}
*/
