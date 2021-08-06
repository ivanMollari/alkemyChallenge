package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.CartoonCharacterNameAndImgDTO;
import com.example.challengealkemy.DTO.CartoonCharacterDetailsDTO;
import com.example.challengealkemy.Models.CartoonCharacter;
import com.example.challengealkemy.Models.MovieOrSerie;
import com.example.challengealkemy.Repositories.CartoonCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartoonCharacterServiceImpl implements CartoonCharacterService {

    private final CartoonCharacterRepository cartoonCharacterRepository;
    private final MovieOrSerieServiceImpl movieOrSerieService;

    @Autowired
    public CartoonCharacterServiceImpl(CartoonCharacterRepository cartoonCharacterRepository,
                                       MovieOrSerieServiceImpl movieOrSerieService) {
        this.cartoonCharacterRepository = cartoonCharacterRepository;
        this.movieOrSerieService = movieOrSerieService;
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
                    movieOrSerieService.getMoviesOrSeriesOfaCharacter(cartoonCharacter.getListMoviesOrSeries())
            );
            cartoonCharacterDetailsDTOList.add(newCartoonCharacter);
        }

        return cartoonCharacterDetailsDTOList;
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

    private Boolean listOfMoviesOrSeriesIsValidToUpdate(List<MovieOrSerie> listMoviesOrSeries) {
        return listMoviesOrSeries != null;
    }

    private Boolean nameIsValidToAdd(CartoonCharacter cartoonCharacter) {
        return cartoonCharacter.getName() == null && cartoonCharacter.getName().length() < 3;
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
                cartoonCharacterRepository.getAllCartoonCharactersByAge(Integer.parseInt(param.get("age")));
        return getCartoonCharacterNameAndImgDTOList(cartoonCharacterList);
    }

    private List<CartoonCharacterNameAndImgDTO> getCartoonsCharactersByIdMovie(HashMap<String, String> param) {
        MovieOrSerie searchedMovie = movieOrSerieService.getMovieById(Long.parseLong(param.get("movies")));
        List<CartoonCharacter> cartoonCharacterList = searchedMovie.getListCartoonCharacters();
        return getCartoonCharacterNameAndImgDTOList(cartoonCharacterList);
    }
}