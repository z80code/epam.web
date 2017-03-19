package by.gsu.epamlab.models;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewFilm {

    private int filmId;
    private String title;
    private Date date;
    private Director director;
    private List<Actor> actors;
    private String description;
    private String image;
    private List<Genre> genres;

    private transient Film film;

    public ViewFilm(int filmId,
                    String title,
                    Date year,
                    Director director,
                    List<Actor> actors,
                    String description,
                    String image,
                    List<Genre> genres) {
        this.filmId = filmId;
        this.title = title;
        this.date = year;
        this.director = director;
        this.actors = actors;
        this.description = description;
        this.image = image;
        this.genres = genres;
    }

    public ViewFilm(Film film) {
        this.film = film;
    }


    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return date;
    }

    public void setYear(Date year) {
        this.date = year;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public ViewFilm getInstance(CinemaDAO cinemaService) throws SQLException {
        List<Cast> casts = cinemaService.getCastRepository().getByFilmId(film.getId());
        List<Integer> actorsIdsList = new ArrayList<>(casts.size());
        for (Cast cast : casts) {
            actorsIdsList.add(cast.getActorId());
        }
        Integer[] actorsIds = actorsIdsList.toArray(new Integer[0]);

        List<Actor> actors = cinemaService.getActorRepository().getByIds(actorsIds);

        List<FilmGenre> filmGenres = cinemaService.getFilmGenreRepository().getByFilmId(film.getId());

        List<Integer> genresIdsList = new ArrayList<>(filmGenres.size());
        for (FilmGenre filmGenre : filmGenres) {
            genresIdsList.add(filmGenre.getGenreId());
        }
        Integer[] filmGenreIds = genresIdsList.toArray(new Integer[0]);

        List<Genre> genres = cinemaService.getGenreRepository().getByIds(filmGenreIds);
        return new ViewFilm(
                film.getId(),
                film.getTitle(),
                film.getRelease(),
                cinemaService.getDirectorRepository().getById(film.getDirectorId()),
                actors,
                film.getDescription(),
                film.getImage(),
                genres

        );
    }
}