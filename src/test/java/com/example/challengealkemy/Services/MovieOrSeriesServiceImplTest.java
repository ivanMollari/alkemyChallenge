package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.CartoonCharacter;
import com.example.challengealkemy.Models.Genre;
import com.example.challengealkemy.Models.MovieOrSeries;
import com.example.challengealkemy.Repositories.MovieOrSeriesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieOrSeriesServiceImplTest {

    @Mock
    private MovieOrSeriesRepository movieOrSeriesRepository;
    @Mock
    private GenreServiceImpl genreService;
    @InjectMocks
    private MovieOrSeriesServiceImpl movieOrSeriesService;

    @Test
    void itShouldBringTheMovieById() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                1L,
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        when(movieOrSeriesRepository.getById(movie.getId())).thenReturn(movie);

        MovieOrSeries expected = movieOrSeriesService.getMovieById(movie.getId());

        assertThat(expected).isNotNull();
        verify(movieOrSeriesRepository, times(1)).getById(movie.getId());
    }

    @Test
    void itShouldGetAllMoviesOrSeriesList() {
        HashMap<String, String> request = new HashMap<>();
        request.put("", "");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);

        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        movieOrSeriesList.add(movie);
        movieOrSeriesList.add(movie2);

        when(movieOrSeriesRepository.findAll()).thenReturn(movieOrSeriesList);

        List<MovieOrSeriesTitleImgDateDTO> expectedMovieOrSeriesTitleImgDateDTOList =
                movieOrSeriesService.getMoviesOrSeriesList(request);

        verify(movieOrSeriesRepository, times(1)).findAll();
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.size()).isEqualTo(2);


    }

    @Test
    void itShouldGetAllMoviesOrSeriesListWithNameAsParam() {
        HashMap<String, String> request = new HashMap<>();
        request.put("name", "Simba");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setTitle("Las aventuras del pato Lucas");
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setTitle("Simba");
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);

        when(movieOrSeriesRepository.findByTitle(request.get("name"))).thenReturn(Optional.of(movie2));

        List<MovieOrSeriesTitleImgDateDTO> expectedMovieOrSeriesTitleImgDateDTOList =
                movieOrSeriesService.getMoviesOrSeriesList(request);

        verify(movieOrSeriesRepository, times(1)).findByTitle(request.get("name"));
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.size()).isEqualTo(1);
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(0).getTitle()).isEqualTo(movie2.getTitle());

    }

    @Test
    void willThrowWhenTheTitleOfTheMoviePassedByParameterDoesNotExist() {
        HashMap<String, String> request = new HashMap<>();
        request.put("name", "El rey Leon");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setTitle("Las aventuras del pato Lucas");
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setTitle("Simba");
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);

        when(movieOrSeriesRepository.findByTitle(request.get("name"))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieOrSeriesService.getMoviesOrSeriesList(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("This movie or series does not exist");

    }

    @Test
    void itShouldGetAllMoviesOrSeriesListWithGenreAsParam() {
        HashMap<String, String> request = new HashMap<>();
        request.put("genre", "1");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        List<Genre> genreList1 = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setListMoviesOrSeries(movieOrSeriesList);
        genreList.add(genre);

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setTitle("Las aventuras del pato Lucas");
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        movieOrSeriesList.add(movie);
        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setTitle("Simba");
        movie2.setListGender(genreList1);
        movie2.setListCartoonCharacters(cartoonCharacterList);
        MovieOrSeries movie3 = new MovieOrSeries();
        movie3.setId(3L);
        movie3.setTitle("La sirenita");
        movie3.setListGender(genreList);
        movie3.setListCartoonCharacters(cartoonCharacterList);
        movieOrSeriesList.add(movie3);

        when(genreService.findById(Long.parseLong(request.get("genre")))).thenReturn(genre);

        List<MovieOrSeriesTitleImgDateDTO> expectedMovieOrSeriesTitleImgDateDTOList =
                movieOrSeriesService.getMoviesOrSeriesList(request);

        verify(genreService, times(1)).findById(Long.parseLong(request.get("genre")));
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.size()).isEqualTo(2);
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(0).getTitle()).isEqualTo(movie.getTitle());
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(1).getTitle()).isEqualTo(movie3.getTitle());

    }

    @Test
    void itShouldGetAllMoviesOrSeriesListSortedByAscParameter() {
        HashMap<String, String> request = new HashMap<>();
        request.put("order", "ASC");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setTitle("Las aventuras del pato Lucas");
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        movie.setCreationDate(LocalDate.of(2000, JANUARY, 6));

        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setTitle("Simba");
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);
        movie2.setCreationDate(LocalDate.of(2000, JANUARY, 5));

        MovieOrSeries movie3 = new MovieOrSeries();
        movie3.setId(3L);
        movie3.setTitle("La sirenita");
        movie3.setListGender(genreList);
        movie3.setListCartoonCharacters(cartoonCharacterList);
        movie3.setCreationDate(LocalDate.of(2010, JANUARY, 5));

        List<MovieOrSeries> movieOrSeriesTitleImgDateDTOList = new ArrayList<>();
        movieOrSeriesTitleImgDateDTOList.add(movie2);
        movieOrSeriesTitleImgDateDTOList.add(movie);
        movieOrSeriesTitleImgDateDTOList.add(movie3);

        when(movieOrSeriesRepository.findAllMoviesOrSeriesByCreationDateInOrderASC())
                .thenReturn(movieOrSeriesTitleImgDateDTOList);

        List<MovieOrSeriesTitleImgDateDTO> expectedMovieOrSeriesTitleImgDateDTOList =
                movieOrSeriesService.getMoviesOrSeriesList(request);

        verify(movieOrSeriesRepository, times(1))
                .findAllMoviesOrSeriesByCreationDateInOrderASC();

        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.size()).isEqualTo(3);
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(0).getTitle()).isEqualTo(movie2.getTitle());
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(1).getTitle()).isEqualTo(movie.getTitle());
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(2).getTitle()).isEqualTo(movie3.getTitle());

    }

    @Test
    void itShouldGetAllMoviesOrSeriesListSortedByDescParameter() {
        HashMap<String, String> request = new HashMap<>();
        request.put("order", "DESC");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setTitle("Las aventuras del pato Lucas");
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        movie.setCreationDate(LocalDate.of(2000, JANUARY, 6));

        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setTitle("Simba");
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);
        movie2.setCreationDate(LocalDate.of(2000, JANUARY, 5));

        MovieOrSeries movie3 = new MovieOrSeries();
        movie3.setId(3L);
        movie3.setTitle("La sirenita");
        movie3.setListGender(genreList);
        movie3.setListCartoonCharacters(cartoonCharacterList);
        movie3.setCreationDate(LocalDate.of(2010, JANUARY, 5));

        List<MovieOrSeries> movieOrSeriesTitleImgDateDTOList = new ArrayList<>();
        movieOrSeriesTitleImgDateDTOList.add(movie3);
        movieOrSeriesTitleImgDateDTOList.add(movie);
        movieOrSeriesTitleImgDateDTOList.add(movie2);

        when(movieOrSeriesRepository.findAllMoviesOrSeriesByCreationDateInOrderDESC())
                .thenReturn(movieOrSeriesTitleImgDateDTOList);

        List<MovieOrSeriesTitleImgDateDTO> expectedMovieOrSeriesTitleImgDateDTOList =
                movieOrSeriesService.getMoviesOrSeriesList(request);

        verify(movieOrSeriesRepository, times(1))
                .findAllMoviesOrSeriesByCreationDateInOrderDESC();

        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.size()).isEqualTo(3);
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(0).getTitle()).isEqualTo(movie3.getTitle());
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(1).getTitle()).isEqualTo(movie.getTitle());
        assertThat(expectedMovieOrSeriesTitleImgDateDTOList.get(2).getTitle()).isEqualTo(movie2.getTitle());

    }

    @Test
    void willThrowWhenTheOrderPassedByParameterIsNotValid() {
        HashMap<String, String> request = new HashMap<>();
        request.put("order", "asv");

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setTitle("Las aventuras del pato Lucas");
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        movie.setCreationDate(LocalDate.of(2000, JANUARY, 6));

        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setTitle("Simba");
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);
        movie2.setCreationDate(LocalDate.of(2000, JANUARY, 5));

        MovieOrSeries movie3 = new MovieOrSeries();
        movie3.setId(3L);
        movie3.setTitle("La sirenita");
        movie3.setListGender(genreList);
        movie3.setListCartoonCharacters(cartoonCharacterList);
        movie3.setCreationDate(LocalDate.of(2010, JANUARY, 5));

        assertThatThrownBy(() -> movieOrSeriesService.getMoviesOrSeriesList(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("The order parameter is not valid");

    }

    @Test
    void itShouldGetAllAttributesOfMoviesOrSeries() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);
        movie.setListGender(genreList);
        movie.setListCartoonCharacters(cartoonCharacterList);
        MovieOrSeries movie2 = new MovieOrSeries();
        movie2.setId(2L);
        movie2.setListGender(genreList);
        movie2.setListCartoonCharacters(cartoonCharacterList);

        List<MovieOrSeries> movieOrSeriesList = new ArrayList<>();
        movieOrSeriesList.add(movie);
        movieOrSeriesList.add(movie2);

        when(movieOrSeriesRepository.findAll()).thenReturn(movieOrSeriesList);

        List<MovieOrSeriesDetailsDTO> expectedMovieOrSeriesDetailsDTOList = movieOrSeriesService.getAllAttributesOfMoviesOrSeries();

        verify(movieOrSeriesRepository).findAll();
        assertThat(expectedMovieOrSeriesDetailsDTOList.size()).isEqualTo(2);
    }

    @Test
    void itShouldAddNewMovieOrSeries() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                1L,
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        movieOrSeriesService.addNewMovieOrSeries(movie);

        ArgumentCaptor<MovieOrSeries> movieOrSeriesArgumentCaptor =
                ArgumentCaptor.forClass(MovieOrSeries.class);

        verify(movieOrSeriesRepository).save(movieOrSeriesArgumentCaptor.capture());

        MovieOrSeries capturedMovieOrSeries = movieOrSeriesArgumentCaptor.getValue();

        assertThat(capturedMovieOrSeries).isEqualTo(movie);

    }

    @Test
    void willThrowWhenTitleIsTaken() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                1L,
                "Las aventuras del Pato Lucas",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        given(movieOrSeriesRepository.findByTitle(movie.getTitle())).willReturn(Optional.of(movie));

        assertThatThrownBy(() -> movieOrSeriesService.addNewMovieOrSeries(movie))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("This movie or series already exists");

    }

    @Test
    void willThrowWhenTitleIsNotValid() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movie = new MovieOrSeries(
                1L,
                "",
                "src//main//resources//static/img/lasAventurasDelPatoLucas.jpg",
                LocalDate.of(2000, JANUARY, 5),
                4,
                cartoonCharacterList,
                genreList
        );

        assertThatThrownBy(() -> movieOrSeriesService.addNewMovieOrSeries(movie))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("This title is not valid");

    }

    @Test
    void itShouldDeleteMovieOrSeriesById() {

        MovieOrSeries movie = new MovieOrSeries();
        movie.setId(1L);

        movieOrSeriesService.deleteMovieOrSeries(movie.getId());
        verify(movieOrSeriesRepository).deleteById(movie.getId());
    }

    @Test
    void willThrowWhenTheWantedMovieOrSeriesDoesNotExist() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setTitle("Simba");

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setTitle("El rey leon");

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Movie o series with id:" +
                        movieToUpdate.getId() + " does not exists");

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

    }

    @Test
    void willThrowWhenTheTheTitleIsAlreadyRegistered() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setTitle("Simba");

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setTitle("El rey leon");

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));
        when(movieOrSeriesRepository.findByTitle(movieUpdate.getTitle())).thenReturn(Optional.of(movieUpdate));

        assertThatThrownBy(() -> movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("This title is already registered");

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());
        verify(movieOrSeriesRepository, times(1)).findByTitle(movieUpdate.getTitle());

    }

    @Test
    void itShouldUpdateMovieOrSeriesWithValidTitle() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setTitle("Simba");

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setTitle("El rey leon");

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));
        when(movieOrSeriesRepository.findByTitle(movieUpdate.getTitle())).thenReturn(Optional.empty());

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());
        verify(movieOrSeriesRepository, times(1)).findByTitle(movieUpdate.getTitle());

        assertThat(movieToUpdate.getTitle()).isEqualTo(movieUpdate.getTitle());
    }

    @Test
    void itShouldNotUpdateMovieOrSeriesWithInvalidTitle() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setTitle("Simba");

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setTitle("");

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getTitle()).isNotEqualTo(movieUpdate.getTitle());
    }

    @Test
    void itShouldUpdateMovieOrSeriesWithValidUrlImg() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setUrlImg("src//main//resources//static/img/simba.jpg");

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setUrlImg("src//main//resources//static/img/elReyLeon.jpg");

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getUrlImg()).isEqualTo(movieUpdate.getUrlImg());
    }

    @Test
    void itShouldNotUpdateMovieOrSeriesWithInvalidUrlImg() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setUrlImg("src//main//resources//static/img/simba.jpg");

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setUrlImg("aa");

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getUrlImg()).isNotEqualTo(movieUpdate.getUrlImg());
    }

    @Test
    void itShouldUpdateMovieOrSeriesWithValidCreationDate() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setCreationDate(LocalDate.of(2000, JANUARY, 5));

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setCreationDate(LocalDate.of(1997, JANUARY, 29));

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getCreationDate()).isEqualTo(movieUpdate.getCreationDate());
    }

    @Test
    void itShouldNotUpdateMovieOrSeriesWithInvalidCreationDate() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setCreationDate(LocalDate.of(2000, JANUARY, 5));

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setCreationDate(null);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getCreationDate()).isNotEqualTo(movieUpdate.getCreationDate());
    }

    @Test
    void itShouldUpdateMovieOrSeriesWithValidScore() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setScore(4);

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setScore(1);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getScore()).isEqualTo(movieUpdate.getScore());
    }

    @Test
    void itShouldNotUpdateMovieOrSeriesWithInvalidScore() {

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setScore(4);

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setScore(10);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getScore()).isNotEqualTo(movieUpdate.getScore());
    }

    @Test
    void itShouldUpdateMovieOrSeriesWithValidListOfCartoonCharacters() {

        List<CartoonCharacter> cartoonCharacterListToUpdate = new ArrayList<>();
        List<CartoonCharacter> cartoonCharacterListUpdate = new ArrayList<>();

        CartoonCharacter Lucas = new CartoonCharacter();
        CartoonCharacter Simba = new CartoonCharacter();
        CartoonCharacter Ariel = new CartoonCharacter();
        CartoonCharacter Bella = new CartoonCharacter();

        cartoonCharacterListToUpdate.add(Lucas);
        cartoonCharacterListToUpdate.add(Bella);

        cartoonCharacterListUpdate.add(Simba);
        cartoonCharacterListUpdate.add(Ariel);

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setListCartoonCharacters(cartoonCharacterListToUpdate);

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setListCartoonCharacters(cartoonCharacterListUpdate);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getListCartoonCharacters()).isEqualTo(movieUpdate.getListCartoonCharacters());
        assertThat(movieToUpdate.getListCartoonCharacters().get(0))
                .isEqualTo(movieUpdate.getListCartoonCharacters().get(0));
        assertThat(movieToUpdate.getListCartoonCharacters().get(1))
                .isEqualTo(movieUpdate.getListCartoonCharacters().get(1));
    }

    @Test
    void itShouldNotUpdateMovieOrSeriesWithInvalidCartoonCharacterList() {

        List<CartoonCharacter> cartoonCharacterList = new ArrayList<>();

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setListCartoonCharacters(cartoonCharacterList);

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setListCartoonCharacters(null);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getListCartoonCharacters()).isNotEqualTo(movieUpdate.getListCartoonCharacters());
    }

    @Test
    void itShouldUpdateMovieOrSeriesWithValidListOfGenre() {

        List<Genre> genreListToUpdate = new ArrayList<>();
        List<Genre> genreListUpdate = new ArrayList<>();

        Genre terror = new Genre();
        Genre comedy = new Genre();
        Genre romanticComedy = new Genre();
        Genre mystery = new Genre();

        genreListToUpdate.add(comedy);
        genreListToUpdate.add(romanticComedy);

        genreListUpdate.add(terror);
        genreListUpdate.add(mystery);

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setListGender(genreListToUpdate);

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setListGender(genreListUpdate);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getListGender()).isEqualTo(movieUpdate.getListGender());
        assertThat(movieToUpdate.getListGender().get(0))
                .isEqualTo(movieUpdate.getListGender().get(0));
        assertThat(movieToUpdate.getListGender().get(1))
                .isEqualTo(movieUpdate.getListGender().get(1));
    }

    @Test
    void itShouldNotUpdateMovieOrSeriesWithInvalidGenreList() {

        List<Genre> genreList = new ArrayList<>();

        MovieOrSeries movieToUpdate = new MovieOrSeries();
        movieToUpdate.setId(1L);
        movieToUpdate.setListGender(genreList);

        MovieOrSeries movieUpdate = new MovieOrSeries();
        movieUpdate.setId(1L);
        movieUpdate.setListGender(null);

        when(movieOrSeriesRepository.findById(movieToUpdate.getId())).thenReturn(Optional.of(movieToUpdate));

        movieOrSeriesService.updateMovieOrSeries(movieToUpdate.getId(), movieUpdate);

        verify(movieOrSeriesRepository, times(1)).findById(movieToUpdate.getId());

        assertThat(movieToUpdate.getListGender()).isNotEqualTo(movieUpdate.getListGender());
    }
}