package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.MovieOrSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieOrSeriesRepository extends JpaRepository<MovieOrSeries,Long> {

    Optional<MovieOrSeries> findByTitle(String title);

    @Query("SELECT m FROM MovieOrSeries m ORDER BY m.creationDate ASC")
    List<MovieOrSeries> findAllMoviesOrSeriesByCreationDateInOrderASC();

    @Query("SELECT m FROM MovieOrSeries m ORDER BY m.creationDate DESC")
    List<MovieOrSeries> findAllMoviesOrSeriesByCreationDateInOrderDESC();
}
