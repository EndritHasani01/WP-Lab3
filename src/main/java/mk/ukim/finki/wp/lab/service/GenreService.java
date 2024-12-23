package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    public List<Genre> findAll();
    Optional<Genre> findById(Long id);
}
