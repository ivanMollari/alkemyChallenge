package com.example.challengealkemy.Repositories;

import com.example.challengealkemy.Models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp,Long> {
}
