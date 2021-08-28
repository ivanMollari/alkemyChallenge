package com.example.challengealkemy.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
     //   TODO: Regex to validated email
        return true;
    }
}
