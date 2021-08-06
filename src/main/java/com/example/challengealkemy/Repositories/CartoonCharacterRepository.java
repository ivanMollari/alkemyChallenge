package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartoonCharacterRepository extends JpaRepository<CartoonCharacter,Long> {

    Optional<CartoonCharacter> findCartoonCharacterByName(String name);

    CartoonCharacter getCartoonCharacterByName(String name);

    @Query("SELECT c FROM CartoonCharacter c WHERE c.age = ?1")
    List<CartoonCharacter> getAllCartoonCharactersByAge(Integer age);
}
