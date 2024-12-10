package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT a FROM Artist a " +
            "WHERE (:firstName IS NULL OR LOWER(a.firstName) LIKE %:firstName%) " +
            "AND (:lastName IS NULL OR LOWER(a.lastName) LIKE %:lastName%) " +
            "AND (:bio IS NULL OR LOWER(a.bio) LIKE %:bio%)")
    List<Artist> searchArtists(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("bio") String bio);

}
