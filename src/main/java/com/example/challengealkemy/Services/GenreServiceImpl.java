package com.example.challengealkemy.Services;

import com.example.challengealkemy.Models.Genre;
import com.example.challengealkemy.Repositories.GenreRepository;
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
