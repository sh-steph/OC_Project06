package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.ThemeDto;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.payload.request.ThemeRequest;
import com.openclassrooms.mdd.mddapp.services.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theme")
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Theme theme = this.themeService.getThemeById(Long.valueOf(id));
        if (theme == null) {
            return ResponseEntity.notFound().build();
        }
//            get theme
        ThemeDto themeDto = new ThemeDto();
        themeDto.setId(theme.getId());
        themeDto.setTitle(theme.getTitle());
        themeDto.setCreatedAt(theme.getCreatedAt());
        themeDto.setUpdatedAt(theme.getUpdatedAt());
        return ResponseEntity.ok().body(themeDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllThemes() {
        themeService.getAllThemes();
        return ResponseEntity.ok(new ThemeRequest.ThemesResponse(themeService.getAllThemes()));
    }

    @PostMapping()
    public ResponseEntity<?> addNewTheme(@Valid @RequestBody ThemeDto themeDto) {
        this.themeService.addNewTheme(themeDto);
        return ResponseEntity.ok().body(new PostRequest.MessageResponse("The theme was successfully created"));
    }
}
