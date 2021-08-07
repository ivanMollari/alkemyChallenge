package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.CartoonCharacterDTO;
import com.example.challengealkemy.DTO.CartoonCharacterNameAndImgDTO;
import com.example.challengealkemy.DTO.CartoonCharacterDetailsDTO;
import com.example.challengealkemy.Models.CartoonCharacter;

import java.util.HashMap;
import java.util.List;

public interface CartoonCharacterService {

    List<CartoonCharacterNameAndImgDTO> getCartoonCharacters(HashMap<String, String> param);

    List<CartoonCharacterDetailsDTO> getAllAttributesOfCartoonCharacters();

    void addNewCartoonCharacter(CartoonCharacter cartoonCharacter);

    void updateCartoonCharacter(Long id, CartoonCharacter cartoonCharacter);

    void deleteCartoonCharacter(Long id);
}
