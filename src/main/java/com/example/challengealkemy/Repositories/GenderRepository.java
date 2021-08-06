package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Genre,Long> {
}
