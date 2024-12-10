/*
package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryArtistRepository {
    public List<Artist> findAll(){
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id){
        return DataHolder.artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

    public List<Artist> filterArtists(String firstName, String lastName, String bio) {
        return DataHolder.artists.stream()
                .filter(artist -> (firstName == null || artist.getFirstName().toLowerCase().contains(firstName.toLowerCase())) &&
                        (lastName == null || artist.getLastName().toLowerCase().contains(lastName.toLowerCase())) &&
                        (bio == null || artist.getBio().toLowerCase().contains(bio.toLowerCase())))
                .collect(Collectors.toList());
    }

}
*/
