package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.MovieOrSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieOrSerieRepository extends JpaRepository<MovieOrSerie,Long> {

    @Query("SELECT m FROM MovieOrSerie m LEFT JOIN m.listCartoonCharacters cm WHERE m.id= ?1")
    List<MovieOrSerie> getAllJoinTableById(Long id);

}
