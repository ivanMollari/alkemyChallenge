package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.CartoonCharacterDTO;

import com.example.challengealkemy.DTO.GenreDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.CartoonCharacter;
import com.example.challengealkemy.Models.Genre;
import com.example.challengealkemy.Models.MovieOrSeries;
import com.example.challengealkemy.Repositories.MovieOrSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieOrSeriesServiceImpl implements MovieOrSeriesService {

    private final MovieOrSeriesRepository movieOrSeriesRepository;

    @Autowired
    public MovieOrSeriesServiceImpl(MovieOrSeriesRepository movieOrSeriesRepository) {
        this.movieOrSeriesRepository = movieOrSeriesRepository;
    }


    @Override
    public MovieOrSeries getMovieById(Long id) {
        return movieOrSeriesRepository.getById(id);
    }

    @Override
    public List<MovieOrSeriesTitleImgDateDTO> getMoviesOrSeriesList() {
        List<MovieOrSeries> listMoviesOrSeries = movieOrSeriesRepository.findAll();
        List<MovieOrSeriesTitleImgDateDTO> movieOrSeriesTitleImgDateDTOList = new ArrayList<>();

        for (MovieOrSeries movieOrSeries : listMoviesOrSeries) {
            MovieOrSeriesTitleImgDateDTO newMovieOrSeries = new MovieOrSeriesTitleImgDateDTO(
                    movieOrSeries.getTitle(),
                    movieOrSeries.getUrlImg(),
                    movieOrSeries.getCreationDate()
            );
            movieOrSeriesTitleImgDateDTOList.add(newMovieOrSeries);
        }

        return movieOrSeriesTitleImgDateDTOList;
    }

    @Override
    public List<MovieOrSeriesDetailsDTO> getAllAttributesOfMoviesOrSeries() {
        List<MovieOrSeries> listMoviesOrSeries = movieOrSeriesRepository.findAll();
        List<MovieOrSeriesDetailsDTO> movieOrSeriesDetailsDTOList = new ArrayList<>();

        for (MovieOrSeries movieOrSeries : listMoviesOrSeries) {
            MovieOrSeriesDetailsDTO newMovieOrSeries = new MovieOrSeriesDetailsDTO(
                    movieOrSeries.getId(),
                    movieOrSeries.getTitle(),
                    movieOrSeries.getUrlImg(),
                    movieOrSeries.getCreationDate(),
                    movieOrSeries.getScore(),
                    getCartoonCharacterListOfMovieOrSeries(movieOrSeries.getListCartoonCharacters()),
                    getGenreListOfMovieOrSeries(movieOrSeries.getListGender())
            );
            movieOrSeriesDetailsDTOList.add(newMovieOrSeries);
        }
        return movieOrSeriesDetailsDTOList;
    }

    @Override
    public void addNewMovieOrSeries(MovieOrSeries newMovieOrSeries) {
        Optional<MovieOrSeries> searchedMovieOrSeries =
                movieOrSeriesRepository.findByTitle(newMovieOrSeries.getTitle());
        if (searchedMovieOrSeries.isPresent()) {
            throw new IllegalStateException("This movie or series already exists");
        }
        if (titleIsValidToAdd(newMovieOrSeries)) {
            throw new IllegalStateException("This title is not valid");
        }
        movieOrSeriesRepository.save(newMovieOrSeries);
    }

    @Override
    public void deleteMovieOrSeries(Long id) {
        movieOrSeriesRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateMovieOrSeries(Long id, MovieOrSeries movieOrSeries) {

        MovieOrSeries searchedMovieOrSeries = movieOrSeriesRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("Movie o series with id:" +
                        id + "does not exists"));

        if (titleIsValidToUpdate(movieOrSeries.getTitle(), searchedMovieOrSeries)) {
            Optional<MovieOrSeries> movieOrSeriesOptional = movieOrSeriesRepository
                    .findByTitle(movieOrSeries.getTitle());

            if (movieOrSeriesOptional.isPresent()) {
                throw new IllegalStateException("this title is already registered");
            }
            searchedMovieOrSeries.setTitle(movieOrSeries.getTitle());
        }

        if(urlImgIsValidToUpdate(movieOrSeries.getUrlImg(),searchedMovieOrSeries)){
            searchedMovieOrSeries.setUrlImg(movieOrSeries.getUrlImg());
        }

        if(creationDateIsValidToUpdate(movieOrSeries.getCreationDate(),searchedMovieOrSeries)){
            searchedMovieOrSeries.setCreationDate(movieOrSeries.getCreationDate());
        }

        if(scoreIsValidToUpdate(movieOrSeries.getScore(),searchedMovieOrSeries)){
            searchedMovieOrSeries.setScore(movieOrSeries.getScore());
        }

        if(listOfCharactersIsValidToUpdate(movieOrSeries.getListCartoonCharacters())){
            searchedMovieOrSeries.setListCartoonCharacters(movieOrSeries.getListCartoonCharacters());
        }

        if(listOfGenreIsValidToUpdate(movieOrSeries.getListGender())){
            searchedMovieOrSeries.setListGender(movieOrSeries.getListGender());
        }

    }

    private boolean listOfGenreIsValidToUpdate(List<Genre> listGender) {
        return listGender != null;
    }

    private boolean listOfCharactersIsValidToUpdate(List<CartoonCharacter> listCartoonCharacters) {
        return listCartoonCharacters != null;
    }

    private boolean scoreIsValidToUpdate(Integer score, MovieOrSeries searchedMovieOrSeries) {
        return score != null && score > 0 && score < 6 && !Objects.equals(searchedMovieOrSeries.getScore(),score);
    }

    private boolean creationDateIsValidToUpdate(LocalDate creationDate, MovieOrSeries searchedMovieOrSeries) {
        return creationDate != null && !Objects.equals(searchedMovieOrSeries.getCreationDate(),creationDate);
    }

    private boolean urlImgIsValidToUpdate(String urlImg, MovieOrSeries searchedMovieOrSeries) {
        return urlImg != null && !Objects.equals(searchedMovieOrSeries.getUrlImg(),urlImg);
    }

    private boolean titleIsValidToUpdate(String title, MovieOrSeries searchedMovieOrSeries) {
        return title != null && title.length() > 3 && !Objects.equals(searchedMovieOrSeries.getTitle(), title);
    }

    private boolean titleIsValidToAdd(MovieOrSeries newMovieOrSeries) {
        return newMovieOrSeries.getTitle() == null || newMovieOrSeries.getTitle().length() < 3;
    }

    private List<CartoonCharacterDTO> getCartoonCharacterListOfMovieOrSeries(List<CartoonCharacter> cartoonCharacterList) {
        List<CartoonCharacterDTO> cartoonCharacterDTOList = new ArrayList<>();

        for (CartoonCharacter cartoonCharacter : cartoonCharacterList) {
            CartoonCharacterDTO newCartoonCharacter = new CartoonCharacterDTO(
                    cartoonCharacter.getId(),
                    cartoonCharacter.getName(),
                    cartoonCharacter.getUrlImg(),
                    cartoonCharacter.getAge(),
                    cartoonCharacter.getWeight(),
                    cartoonCharacter.getHistory()
            );
            cartoonCharacterDTOList.add(newCartoonCharacter);
        }
        return cartoonCharacterDTOList;
    }

    private List<GenreDTO> getGenreListOfMovieOrSeries(List<Genre> genreList) {

        List<GenreDTO> genreDTOList = new ArrayList<>();

        for (Genre genre : genreList) {
            GenreDTO newGenre = new GenreDTO(
                    genre.getId(),
                    genre.getName(),
                    genre.getUrlImg()
            );
            genreDTOList.add(newGenre);
        }
        return genreDTOList;
    }

}
