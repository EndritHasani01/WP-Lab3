package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByAlbum_Id(Long albumId);

    /*
    * @Query("SELECT e FROM Event e WHERE e.name LIKE %:keyword%")
    * List<Event> searchEvents(@Param("keyword") String keyword);
    * */
}
