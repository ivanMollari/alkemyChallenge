package com.example.challengealkemy.services;

import com.example.challengealkemy.dto.CartoonCharacterNameAndImgDTO;
import com.example.challengealkemy.dto.CartoonCharacterDetailsDTO;
import com.example.challengealkemy.models.CartoonCharacter;

import java.util.HashMap;
import java.util.List;

public interface CartoonCharacterService {

    List<CartoonCharacterNameAndImgDTO> getCartoonCharacters(HashMap<String, String> param);

    List<CartoonCharacterDetailsDTO> getAllAttributesOfCartoonCharacters();

    void addNewCartoonCharacter(CartoonCharacter cartoonCharacter);

    void updateCartoonCharacter(Long id, CartoonCharacter cartoonCharacter);

    void deleteCartoonCharacter(Long id);
}
