package com.example.challengealkemy.services;

import com.example.challengealkemy.models.Genre;
import com.example.challengealkemy.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

   private final GenreRepository genreRepository;

   @Autowired
   public GenreServiceImpl(GenreRepository genreRepository){
       this.genreRepository = genreRepository;
   }

    @Override
    public Genre findById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if(genre.isEmpty()){
            throw new IllegalStateException("this genre does not exist");
        }
        return genre.get();
    }
}
