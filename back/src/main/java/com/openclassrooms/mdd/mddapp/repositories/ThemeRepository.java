package com.openclassrooms.mdd.mddapp.repositories;

import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
