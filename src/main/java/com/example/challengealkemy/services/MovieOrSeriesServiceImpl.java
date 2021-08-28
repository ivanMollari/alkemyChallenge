package com.example.challengealkemy.services;

import com.example.challengealkemy.dto.CartoonCharacterDTO;

import com.example.challengealkemy.dto.GenreDTO;
import com.example.challengealkemy.dto.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.dto.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.models.CartoonCharacter;
import com.example.challengealkemy.models.Genre;
import com.example.challengealkemy.models.MovieOrSeries;
import com.example.challengealkemy.repositories.MovieOrSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class MovieOrSeriesServiceImpl implements MovieOrSeriesService {

    private final MovieOrSeriesRepository movieOrSeriesRepository;
    private final GenreServiceImpl genreService;

    @Autowired
    public MovieOrSeriesServiceImpl(MovieOrSeriesRepository movieOrSeriesRepository,
                                    GenreServiceImpl genreService) {
        this.movieOrSeriesRepository = movieOrSeriesRepository;
        this.genreService = genreService;
    }


    @Override
    public Optional<MovieOrSeries> getMovieById(Long id) {
        return movieOrSeriesRepository.findById(id);
    }

    @Override
    public List<MovieOrSeriesTitleImgDateDTO> getMoviesOrSeriesList(HashMap<String, String> param) {
        List<MovieOrSeries> listMoviesOrSeries = movieOrSeriesRepository.findAll();
        List<MovieOrSeriesTitleImgDateDTO> movieOrSeriesTitleImgDateDTOList = new ArrayList<>();

        if(param != null){
            if(param.containsKey("name")){
                if(param.get("name") != null){
                    movieOrSeriesTitleImgDateDTOList.add(getMovieOrSeriesByNameParam(param));
                }
            } else if (param.containsKey("genre")){
                if(param.get("genre") != null){
                    movieOrSeriesTitleImgDateDTOList = getMovieOrSeriesByIdGenreParam(param);
                }
            } else if(param.containsKey("order")){
                if(param.get("order") != null){
                    movieOrSeriesTitleImgDateDTOList = getMoviesOrSeriesInOrderByParam(param);
                }
            } else {
                movieOrSeriesTitleImgDateDTOList = getMovieOrSeriesTitleImgDateDTOList(listMoviesOrSeries);
            }
        }

        return movieOrSeriesTitleImgDateDTOList;
    }

    private List<MovieOrSeriesTitleImgDateDTO> getMoviesOrSeriesInOrderByParam(HashMap<String, String> param) {
        List<MovieOrSeriesTitleImgDateDTO> movieOrSeriesTitleImgDateDTOList;

        if(param.get("order").equals("ASC")){
            movieOrSeriesTitleImgDateDTOList = getMovieOrSeriesTitleImgDateDTOList(
                    movieOrSeriesRepository.findAllByOrderByCreationDateAsc()
            );
        }else if (param.get("order").equals("DESC")){
            movieOrSeriesTitleImgDateDTOList = getMovieOrSeriesTitleImgDateDTOList(
                    movieOrSeriesRepository.findAllByOrderByCreationDateDesc()
            );
        }else{
            throw new IllegalStateException("The order parameter is not valid");
        }
        return movieOrSeriesTitleImgDateDTOList;
    }

    private List<MovieOrSeriesTitleImgDateDTO> getMovieOrSeriesByIdGenreParam(HashMap<String, String> param) {
        Genre searchedGenre = genreService.findById(Long.parseLong(param.get("genre")));
        List<MovieOrSeries> movieOrSeriesList = searchedGenre.getListMoviesOrSeries();
        return getMovieOrSeriesTitleImgDateDTOList(movieOrSeriesList);
    }

    private MovieOrSeriesTitleImgDateDTO getMovieOrSeriesByNameParam(HashMap<String, String> param) {
        Optional<MovieOrSeries> searchedMovieOrSeries = movieOrSeriesRepository.findByTitle(param.get("name"));
        if(searchedMovieOrSeries.isEmpty()){
            throw new IllegalStateException("This movie or series does not exist");
        }
        return createNewMovieOrSeriesTitleImgDateDTO(searchedMovieOrSeries.get());
    }

    private MovieOrSeriesTitleImgDateDTO createNewMovieOrSeriesTitleImgDateDTO(MovieOrSeries movieOrSeries) {
        return new MovieOrSeriesTitleImgDateDTO(
                movieOrSeries.getTitle(),
                movieOrSeries.getUrlImg(),
                movieOrSeries.getCreationDate()
        );
    }

    private List<MovieOrSeriesTitleImgDateDTO> getMovieOrSeriesTitleImgDateDTOList(List<MovieOrSeries> movieOrSeriesList){
        List<MovieOrSeriesTitleImgDateDTO> getMovieOrSeriesTitleImgDateDTOList = new ArrayList<>();

        for (MovieOrSeries movieOrSeries : movieOrSeriesList) {
            MovieOrSeriesTitleImgDateDTO newMovieOrSeries = createNewMovieOrSeriesTitleImgDateDTO(movieOrSeries);
            getMovieOrSeriesTitleImgDateDTOList.add(newMovieOrSeries);
        }
        return getMovieOrSeriesTitleImgDateDTOList;
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
                        id + " does not exists"));

        if (titleIsValidToUpdate(movieOrSeries.getTitle(), searchedMovieOrSeries)) {
            Optional<MovieOrSeries> movieOrSeriesOptional = movieOrSeriesRepository
                    .findByTitle(movieOrSeries.getTitle());

            if (movieOrSeriesOptional.isPresent()) {
                throw new IllegalStateException("This title is already registered");
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
        return urlImg != null && urlImg.length() > 30 && !Objects.equals(searchedMovieOrSeries.getUrlImg(),urlImg);
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
