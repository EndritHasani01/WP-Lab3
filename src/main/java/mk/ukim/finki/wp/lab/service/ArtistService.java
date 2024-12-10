package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
   /* List<Artist> filterArtists(String firstName, String lastName, String bio);*/
   List<Artist> searchArtists(
           @Param("firstName") String firstName,
           @Param("lastName") String lastName,
           @Param("bio") String bio);
}
