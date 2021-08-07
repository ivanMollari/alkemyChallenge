package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.MovieOrSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieOrSeriesRepository extends JpaRepository<MovieOrSeries,Long> {

    Optional<MovieOrSeries> findByTitle(String title);
}
