package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.dto.ThemeDto;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;

    public Theme getThemeById(Long id) {
        return this.themeRepository.findById(id).orElse(null);
    }

    public Iterable<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public void addNewTheme(ThemeDto themeDto) {
        Theme theme = new Theme();
        theme.setTitle(themeDto.getTitle());
        theme.setCreatedAt(LocalDateTime.now());
        theme.setUpdatedAt(null);
        this.themeRepository.save(theme);
    }
}
