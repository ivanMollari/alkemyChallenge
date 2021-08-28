package com.example.challengealkemy.controllers;

import com.example.challengealkemy.dto.CartoonCharacterNameAndImgDTO;
import com.example.challengealkemy.dto.CartoonCharacterDetailsDTO;
import com.example.challengealkemy.models.CartoonCharacter;
import com.example.challengealkemy.services.CartoonCharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CartoonCharacterController {

    private final CartoonCharacterServiceImpl cartoonCharacterService;


    @Autowired
    public CartoonCharacterController(CartoonCharacterServiceImpl cartoonCharacterService){
        this.cartoonCharacterService=cartoonCharacterService;
    }

    @GetMapping
    public List<CartoonCharacterNameAndImgDTO> getCartoonCharacters(@RequestParam HashMap<String, String> param){
        return cartoonCharacterService.getCartoonCharacters(param);
    }

    @GetMapping("/details")
    public List<CartoonCharacterDetailsDTO> getDetailsOfCartoonCharacters(){
        return cartoonCharacterService.getAllAttributesOfCartoonCharacters();
    }

    @PostMapping
    public void saveNewCartoonCharacter(@RequestBody CartoonCharacter cartoonCharacter){
        cartoonCharacterService.addNewCartoonCharacter(cartoonCharacter);
    }

    @DeleteMapping("/{idCharacter}")
    public void deleteCartoonCharacter(@PathVariable("idCharacter")Long id){
        cartoonCharacterService.deleteCartoonCharacter(id);
    }

    @PutMapping(path = "/{cartoonCharacterId}")
    public void updateCartoonCharacter(@PathVariable("cartoonCharacterId")Long id,
                                       @RequestBody CartoonCharacter cartoonCharacter){
        cartoonCharacterService.updateCartoonCharacter(id,cartoonCharacter);
    }


}
