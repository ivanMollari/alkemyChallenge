package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.CartoonCharacterDTO;

import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.CartoonCharacter;
import com.example.challengealkemy.Models.MovieOrSeries;
import com.example.challengealkemy.Repositories.MovieOrSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                    getCartoonCharacterListOfMovieOrSeries(movieOrSeries.getListCartoonCharacters())
            );
            movieOrSeriesDetailsDTOList.add(newMovieOrSeries);
        }
        return movieOrSeriesDetailsDTOList;
    }

    @Override
    public void addNewMovieOrSeries(MovieOrSeries newMovieOrSeries) {
        Optional<MovieOrSeries> searchedMovieOrSeries =
                movieOrSeriesRepository.findByTitle(newMovieOrSeries.getTitle());
        if(searchedMovieOrSeries.isPresent()){
            throw new IllegalStateException("This movie or series already exists");
        }
        if(titleIsValidToAdd(newMovieOrSeries)){
            throw new IllegalStateException("This title is not valid");
        }
        movieOrSeriesRepository.save(newMovieOrSeries);
    }

    private boolean titleIsValidToAdd(MovieOrSeries newMovieOrSeries) {
        return newMovieOrSeries.getTitle() != null || newMovieOrSeries.getTitle().length() < 3;
    }

    private List<CartoonCharacterDTO> getCartoonCharacterListOfMovieOrSeries(List<CartoonCharacter> cartoonCharacterList) {
        List<CartoonCharacterDTO> cartoonCharacterDTOList = new ArrayList<>();

        for(CartoonCharacter cartoonCharacter : cartoonCharacterList){
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

}
