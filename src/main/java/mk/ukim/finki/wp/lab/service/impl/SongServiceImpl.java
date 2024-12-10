package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;



    public SongServiceImpl(SongRepository  songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        Song existingSong = songRepository.findById(song.getTrackId())
                .orElseThrow( () -> new SongNotFoundException(song.getTrackId()));

        if(!existingSong.getPerformers().contains(artist)){
            existingSong.getPerformers().add(artist);
            songRepository.save(existingSong);
        }

        return artist;
    }

    @Override
    public Song findByTrackId(Long trackId) {
        return songRepository.findById(trackId).orElse(null);
    }

    @Override
    public Optional<Song> save(String title, String genre, Integer releaseYear, Album album) {
        Song song = new Song(title, genre, releaseYear, album);
        return Optional.of(songRepository.save(song));
        //return songRepository.save(title, genre, releaseYear, album);
    }

    @Override
    public Optional<Song> update(Long trackId, String title, String genre, Integer releaseYear, Album album) {
        Song existingSong  = songRepository.findById(trackId)
                .orElseThrow(() -> new SongNotFoundException(trackId));

        if(existingSong!=null){
            existingSong.setTitle(title);
            existingSong.setGenre(genre);
            existingSong.setReleaseYear(releaseYear);
            existingSong.setAlbum(album);

            // Save and return the updated song
            return Optional.of(songRepository.save(existingSong));
        }

        return Optional.empty();
    }


    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }
}