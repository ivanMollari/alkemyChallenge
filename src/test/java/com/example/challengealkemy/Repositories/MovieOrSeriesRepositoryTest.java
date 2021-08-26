package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.CartoonCharacter;
import com.example.challengealkemy.Models.Genre;
import com.example.challengealkemy.Models.MovieOrSeries;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class MovieOrSeriesRepositoryTest {

    @Autowired
    private MovieOrSeriesRepository movieOrSeriesRepository;

    @Test
    void itShouldCheckThatTheTitleIsFound() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie);

        Optional<MovieOrSeries> expected = movieOrSeriesRepository.findByTitle("Las aventuras del Pato Lucas");

        assertThat(expected.get()).isNotNull();
    }


    @Test
    void itShouldCheckThatTheMoviesAreSortedInAscOrder() {
        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie);

        MovieOrSeries movie2 = new MovieOrSeries(
                "La Sirenita",
                "src//main//resources//static/img/laSirenita.jpg",
                LocalDate.of(2010, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie2);

        MovieOrSeries movie3 = new MovieOrSeries(
                "La muerte de Simba",
                "src//main//resources//static/img/laMuesteDeSimba.jpg",
                LocalDate.of(1997, JANUARY, 15),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie3);

        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        movieOrSeriesList.add(movie3);
        movieOrSeriesList.add(movie);
        movieOrSeriesList.add(movie2);

        List<MovieOrSeries> expectedList = movieOrSeriesRepository.findAllMoviesOrSeriesByCreationDateInOrderASC();

        assertThat(expectedList).isEqualTo(movieOrSeriesList);

    }

    @Test
    void itShouldCheckThatTheMoviesAreNotSortedInAscOrder() {
        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie);

        MovieOrSeries movie2 = new MovieOrSeries(
                "La Sirenita",
                "src//main//resources//static/img/laSirenita.jpg",
                LocalDate.of(2010, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie2);

        MovieOrSeries movie3 = new MovieOrSeries(
                "La muerte de Simba",
                "src//main//resources//static/img/laMuesteDeSimba.jpg",
                LocalDate.of(1997, JANUARY, 15),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie3);

        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        movieOrSeriesList.add(movie);
        movieOrSeriesList.add(movie3);
        movieOrSeriesList.add(movie2);

        List<MovieOrSeries> expectedList = movieOrSeriesRepository.findAllMoviesOrSeriesByCreationDateInOrderASC();

        assertThat(expectedList).isNotEqualTo(movieOrSeriesList);

    }

    @Test
    void itShouldCheckThatTheMoviesAreSortedInDscOrder() {
        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie);

        MovieOrSeries movie2 = new MovieOrSeries(
                "La Sirenita",
                "src//main//resources//static/img/laSirenita.jpg",
                LocalDate.of(2010, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie2);

        MovieOrSeries movie3 = new MovieOrSeries(
                "La muerte de Simba",
                "src//main//resources//static/img/laMuesteDeSimba.jpg",
                LocalDate.of(1997, JANUARY, 15),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie3);

        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        movieOrSeriesList.add(movie2);
        movieOrSeriesList.add(movie);
        movieOrSeriesList.add(movie3);

        List<MovieOrSeries> expectedList = movieOrSeriesRepository.findAllMoviesOrSeriesByCreationDateInOrderDESC();

        assertThat(expectedList).isEqualTo(movieOrSeriesList);
    }

    @Test
    void itShouldCheckThatTheMoviesAreNotSortedInDscOrder() {
        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie);

        MovieOrSeries movie2 = new MovieOrSeries(
                "La Sirenita",
                "src//main//resources//static/img/laSirenita.jpg",
                LocalDate.of(2010, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie2);

        MovieOrSeries movie3 = new MovieOrSeries(
                "La muerte de Simba",
                "src//main//resources//static/img/laMuesteDeSimba.jpg",
                LocalDate.of(1997, JANUARY, 15),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesRepository.save(movie3);

        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        movieOrSeriesList.add(movie);
        movieOrSeriesList.add(movie2);
        movieOrSeriesList.add(movie3);

        List<MovieOrSeries> expectedList = movieOrSeriesRepository.findAllMoviesOrSeriesByCreationDateInOrderDESC();

        assertThat(expectedList).isNotEqualTo(movieOrSeriesList);
    }
}