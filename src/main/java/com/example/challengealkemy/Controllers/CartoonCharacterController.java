package com.example.challengealkemy.Controllers;

import com.example.challengealkemy.DTO.CartoonCharacterNameAndImgDTO;
import com.example.challengealkemy.DTO.CartoonCharacterDetailsDTO;
import com.example.challengealkemy.Models.CartoonCharacter;
import com.example.challengealkemy.Services.CartoonCharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CartoonCharacterController {

    private final CartoonCharacterServiceImpl cartoonCharacterService;


    @Autowired
    public CartoonCharacterController(CartoonCharacterServiceImpl cartoonCharacterService){
        this.cartoonCharacterService=cartoonCharacterService;
    }

    @GetMapping("/characters")
    public List<CartoonCharacterNameAndImgDTO> getCartoonCharacters(@RequestParam HashMap<String, String> param){
        return cartoonCharacterService.getCartoonCharacters(param);
    }

    @GetMapping("/characters/details")
    public List<CartoonCharacterDetailsDTO> getDetailsOfCartoonCharacters(){
        return cartoonCharacterService.getAllAttributesOfCartoonCharacters();
    }

    @PostMapping("/characters/add")
    public void saveNewCartoonCharacter(@RequestBody CartoonCharacter cartoonCharacter){
        cartoonCharacterService.addNewCartoonCharacter(cartoonCharacter);
    }

    @DeleteMapping("/characters/delete/{idCharacter}")
    public void deleteCartoonCharacter(@PathVariable("idCharacter")Long id){
        cartoonCharacterService.deleteCartoonCharacter(id);
    }

    @PutMapping(path = "/characters/update/{cartoonCharacterId}")
    public void updateCartoonCharacter(@PathVariable("cartoonCharacterId")Long id,
                                       @RequestBody CartoonCharacter cartoonCharacter){
        cartoonCharacterService.updateCartoonCharacter(id,cartoonCharacter);
    }


}
