package com.example.challengealkemy.services;

import com.example.challengealkemy.dto.CartoonCharacterNameAndImgDTO;
import com.example.challengealkemy.dto.CartoonCharacterDetailsDTO;
import com.example.challengealkemy.dto.MovieOrSeriesDTO;
import com.example.challengealkemy.models.CartoonCharacter;
import com.example.challengealkemy.models.MovieOrSeries;
import com.example.challengealkemy.repositories.CartoonCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartoonCharacterServiceImpl implements CartoonCharacterService {

    private final CartoonCharacterRepository cartoonCharacterRepository;
    private final MovieOrSeriesServiceImpl movieOrSeriesService;

    @Autowired
    public CartoonCharacterServiceImpl(CartoonCharacterRepository cartoonCharacterRepository,
                                       MovieOrSeriesServiceImpl movieOrSeriesService) {
        this.cartoonCharacterRepository = cartoonCharacterRepository;
        this.movieOrSeriesService = movieOrSeriesService;
    }


    @Override
    public List<CartoonCharacterNameAndImgDTO> getCartoonCharacters(HashMap<String, String> param) {
        List<CartoonCharacter> listCartoons = cartoonCharacterRepository.findAll();
        List<CartoonCharacterNameAndImgDTO> cartoonCharacterNameAndImgDTOList = new ArrayList<>();

        if(param != null) {
            if (param.containsKey("name")) {
                if (param.get("name") != null) {
                    cartoonCharacterNameAndImgDTOList.add(getCartoonByNameParam(param));
                }
            } else if (param.containsKey("age")) {
                if (param.get("age") != null) {
                    cartoonCharacterNameAndImgDTOList = getCartoonsCharactersByAgeParam(param);
                }
            } else if (param.containsKey("movies")) {
                if (param.get("movies") != null) {
                    cartoonCharacterNameAndImgDTOList = getCartoonsCharactersByIdMovie(param);
                }
            }else {
                cartoonCharacterNameAndImgDTOList = getCartoonCharacterNameAndImgDTOList(listCartoons);
            }
        }
        return cartoonCharacterNameAndImgDTOList;
    }

    @Override
    public List<CartoonCharacterDetailsDTO> getAllAttributesOfCartoonCharacters() {
        List<CartoonCharacter> listCartoons = cartoonCharacterRepository.findAll();
        List<CartoonCharacterDetailsDTO> cartoonCharacterDetailsDTOList = new ArrayList<>();

        for (CartoonCharacter cartoonCharacter : listCartoons) {
            CartoonCharacterDetailsDTO newCartoonCharacter = new CartoonCharacterDetailsDTO(
                    cartoonCharacter.getId(),
                    cartoonCharacter.getName(),
                    cartoonCharacter.getUrlImg(),
                    cartoonCharacter.getAge(),
                    cartoonCharacter.getWeight(),
                    cartoonCharacter.getHistory(),
                    getMoviesOrSeriesOfaCharacter(cartoonCharacter.getListMoviesOrSeries())
            );
            cartoonCharacterDetailsDTOList.add(newCartoonCharacter);
        }

        return cartoonCharacterDetailsDTOList;
    }

    private List<MovieOrSeriesDTO> getMoviesOrSeriesOfaCharacter(List<MovieOrSeries> movieOrSeriesList) {
        List<MovieOrSeriesDTO> movieOrSeriesDTOList = new ArrayList<>();

        for (MovieOrSeries movieOrSeries : movieOrSeriesList) {
            MovieOrSeriesDTO newMovieOrSeries = new MovieOrSeriesDTO(
                    movieOrSeries.getId(),
                    movieOrSeries.getTitle(),
                    movieOrSeries.getUrlImg(),
                    movieOrSeries.getCreationDate(),
                    movieOrSeries.getScore()
            );
            movieOrSeriesDTOList.add(newMovieOrSeries);
        }
        return movieOrSeriesDTOList;
    }

    @Override
    public void addNewCartoonCharacter(CartoonCharacter cartoonCharacter) {
        Optional<CartoonCharacter> searchedCartoonCharacter = cartoonCharacterRepository
                .findCartoonCharacterByName(cartoonCharacter.getName());
        if (searchedCartoonCharacter.isPresent()) {
            throw new IllegalStateException("This cartoon character already exists");
        }
        if (nameIsValidToAdd(cartoonCharacter)) {
            throw new IllegalStateException("This name is not valid");
        }
        cartoonCharacterRepository.save(cartoonCharacter);
    }

    @Override
    public void deleteCartoonCharacter(Long id) {
        cartoonCharacterRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateCartoonCharacter(Long id,
                                       CartoonCharacter cartoonCharacter) {

        CartoonCharacter searchedCartoonCharacter = cartoonCharacterRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cartoon character with id: " +
                        id + "does not exists"));

        if (nameIsValidToUpdate(cartoonCharacter.getName(), searchedCartoonCharacter)) {
            Optional<CartoonCharacter> cartoonCharacterOptional = cartoonCharacterRepository
                    .findCartoonCharacterByName(cartoonCharacter.getName());

            if (cartoonCharacterOptional.isPresent()) {
                throw new IllegalStateException("this name is already registered");
            }
            searchedCartoonCharacter.setName(cartoonCharacter.getName());
        }

        if(urlImgIsValidToUpdate(cartoonCharacter.getUrlImg(),searchedCartoonCharacter)){
            searchedCartoonCharacter.setUrlImg(cartoonCharacter.getUrlImg());
        }

        if (ageIsValidToUpdate(cartoonCharacter.getAge(), searchedCartoonCharacter)) {
            searchedCartoonCharacter.setAge(cartoonCharacter.getAge());
        }

        if (weightIsValidToUpdate(cartoonCharacter.getWeight(), searchedCartoonCharacter)) {
            searchedCartoonCharacter.setWeight(cartoonCharacter.getWeight());
        }

        if (historyIsValidToUpdate(cartoonCharacter.getHistory(), searchedCartoonCharacter)) {
            searchedCartoonCharacter.setHistory(cartoonCharacter.getHistory());
        }

        if (listOfMoviesOrSeriesIsValidToUpdate(cartoonCharacter.getListMoviesOrSeries())) {
            searchedCartoonCharacter.setListMoviesOrSeries(cartoonCharacter.getListMoviesOrSeries());
        }

    }

    private boolean urlImgIsValidToUpdate(String urlImg, CartoonCharacter searchedCartoonCharacter) {
        return urlImg != null && !Objects.equals(searchedCartoonCharacter.getUrlImg(),urlImg);
    }

    private Boolean nameIsValidToUpdate(String name, CartoonCharacter cartoonCharacter) {
        return name != null && name.length() > 3 && !Objects.equals(cartoonCharacter.getName(), name);
    }

    private Boolean ageIsValidToUpdate(Integer age, CartoonCharacter cartoonCharacter) {
        return age != null && !Objects.equals(cartoonCharacter.getAge(), age);
    }

    private Boolean weightIsValidToUpdate(Float weight, CartoonCharacter cartoonCharacter) {
        return weight != null && !Objects.equals(cartoonCharacter.getWeight(), weight);
    }

    private Boolean historyIsValidToUpdate(String history, CartoonCharacter cartoonCharacter) {
        return history != null && !Objects.equals(cartoonCharacter.getHistory(), history);
    }

    private Boolean listOfMoviesOrSeriesIsValidToUpdate(List<MovieOrSeries> listMoviesOrSeries) {
        return listMoviesOrSeries != null;
    }

    private Boolean nameIsValidToAdd(CartoonCharacter cartoonCharacter) {
        return cartoonCharacter.getName() == null || cartoonCharacter.getName().length() < 3;
    }

    private List<CartoonCharacterNameAndImgDTO> getCartoonCharacterNameAndImgDTOList(List<CartoonCharacter> listCartoons) {
        List<CartoonCharacterNameAndImgDTO> getListOfCartoonCharacters = new ArrayList<>();

        for (CartoonCharacter cartoonCharacter : listCartoons) {
            CartoonCharacterNameAndImgDTO newCartoonCharacter = createNewCartoonCharacterNameAndImgDTO(cartoonCharacter);
            getListOfCartoonCharacters.add(newCartoonCharacter);
        }
        return getListOfCartoonCharacters;
    }

    private CartoonCharacterNameAndImgDTO getCartoonByNameParam(HashMap<String, String> param) {
        CartoonCharacter searchedCartoon = cartoonCharacterRepository.getCartoonCharacterByName(param.get("name"));
        return createNewCartoonCharacterNameAndImgDTO(searchedCartoon);
    }

    private CartoonCharacterNameAndImgDTO createNewCartoonCharacterNameAndImgDTO(CartoonCharacter cartoonCharacter) {
        return new CartoonCharacterNameAndImgDTO(
                cartoonCharacter.getName(),
                cartoonCharacter.getUrlImg()
        );
    }

    private List<CartoonCharacterNameAndImgDTO> getCartoonsCharactersByAgeParam(HashMap<String, String> param) {
        List<CartoonCharacter> cartoonCharacterList =
                cartoonCharacterRepository.findByAgeEquals(Integer.parseInt(param.get("age")));
        return getCartoonCharacterNameAndImgDTOList(cartoonCharacterList);
    }

    private List<CartoonCharacterNameAndImgDTO> getCartoonsCharactersByIdMovie(HashMap<String, String> param) {
        List<CartoonCharacterNameAndImgDTO> cartoonCharacterNameAndImgDTOList = new ArrayList<>();
        Optional<MovieOrSeries> searchedMovie = movieOrSeriesService.getMovieById(Long.parseLong(param.get("movies")));
        if(searchedMovie.isPresent()) {
            List<CartoonCharacter> cartoonCharacterList = searchedMovie.get().getListCartoonCharacters();
            cartoonCharacterNameAndImgDTOList = getCartoonCharacterNameAndImgDTOList(cartoonCharacterList);
        }
        return cartoonCharacterNameAndImgDTOList;
    }
}
