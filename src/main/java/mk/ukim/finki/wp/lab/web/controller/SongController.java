package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidAlbumIdException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.GenreService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService, GenreService genreService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String redirectToSongs() {
        return "redirect:/songs";
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }


        model.addAttribute("songs", this.songService.listSongs());

        model.addAttribute("bodyContent","listSongs");
        return "master-template";
    }

    @GetMapping("/songs/add-form")
    public String getAddSongPage(Model model){
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.findAll();
        List<Genre> genres = this.genreService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        model.addAttribute("genres", genres);

        model.addAttribute("bodyContent","add-song");
        return "master-template";
    }

    @PostMapping("/songs/add")
    public String saveSong(@RequestParam(required = false) String title,
                           @RequestParam(required = false) Long trackId,
                           @RequestParam(required = false) Long genreId,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId){

        Album album = albumService.findById(albumId).orElseThrow(() -> new InvalidAlbumIdException(albumId));
        Genre genre = genreService.findById(genreId).orElseThrow(() -> new InvalidAlbumIdException(genreId));

        if(trackId == null){
            this.songService.save(title, genre, releaseYear, album);
        }else{
            songService.update(trackId,title,genre,releaseYear,album);
        }


        return "redirect:/songs";
    }

    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/songs/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model){
        Song song = this.songService.findByTrackId(id);
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.findAll();
        List<Genre> genres = this.genreService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        model.addAttribute("genres", genres);

        model.addAttribute("bodyContent","add-song");
        return "master-template";
    }

    @GetMapping("/songs/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) Long genreId,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId){
        Song song = this.songService.findByTrackId(songId);
        song.setTitle(title);
        Genre genre = this.genreService.findById(genreId).orElse(null);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(albumService.findById(albumId).orElseThrow(() -> new InvalidAlbumIdException(albumId)));
        return "redirect:/songs";
    }

    @GetMapping("/songDetails/{id}")
    public String songDetails(@PathVariable Long id,
                              @RequestParam Long artistId,
                              Model model){

        Song song = this.songService.findByTrackId(id);
        Artist artist = this.artistService.findById(artistId);

        songService.addArtistToSong(artist,song);

        model.addAttribute("song",song);

        model.addAttribute("bodyContent","songDetails");
        return "master-template";
    }
}
