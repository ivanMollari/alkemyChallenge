package com.example.challengealkemy.repositories;

import com.example.challengealkemy.models.MovieOrSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieOrSeriesRepository extends JpaRepository<MovieOrSeries,Long> {

    Optional<MovieOrSeries> findById(Long id);

    Optional<MovieOrSeries> findByTitle(String title);

    List<MovieOrSeries> findAllByOrderByCreationDateAsc();

    List<MovieOrSeries> findAllByOrderByCreationDateDesc();
}
