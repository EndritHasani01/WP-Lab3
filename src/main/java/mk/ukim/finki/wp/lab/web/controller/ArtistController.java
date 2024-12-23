package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtistController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;


    public ArtistController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public String getArtistsPage(@RequestParam Long trackId,
                                 @RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false) String bio,
                                 @RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }


        List<Artist> filteredArtists = artistService.searchArtists(firstName, lastName, bio);
        model.addAttribute("artists",filteredArtists);
        model.addAttribute("song",songService.findByTrackId(trackId));

        model.addAttribute("bodyContent","artistsList");
        return "master-template";
    }

}
