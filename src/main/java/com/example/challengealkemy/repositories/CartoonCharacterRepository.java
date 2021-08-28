package com.example.challengealkemy.repositories;

import com.example.challengealkemy.models.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartoonCharacterRepository extends JpaRepository<CartoonCharacter,Long> {

    Optional<CartoonCharacter> findCartoonCharacterByName(String name);

    CartoonCharacter getCartoonCharacterByName(String name);

    List<CartoonCharacter> findByAgeEquals(Integer age);
}
