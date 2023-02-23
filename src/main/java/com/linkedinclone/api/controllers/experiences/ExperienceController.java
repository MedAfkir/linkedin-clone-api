package com.linkedinclone.api.controllers.experiences;

import com.linkedinclone.api.dto.experiences.ExperienceRequest;
import com.linkedinclone.api.exceptions.notfound.NotFoundException;
import com.linkedinclone.api.services.experiences.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<?> getAllExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExperienceById(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

    @PostMapping
    public ResponseEntity<?> addExperience(@RequestBody ExperienceRequest request) throws NotFoundException {
        return ResponseEntity.ok(experienceService.addExperience(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExperience(@PathVariable("id") Long id, @RequestBody ExperienceRequest request) throws NotFoundException {
        return ResponseEntity.ok(experienceService.updateExperience(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperienceById(@PathVariable("id") Long id) throws NotFoundException {
        experienceService.deleteExperienceById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<?> getExperienceClient(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(experienceService.getExperienceClient(id));
    }
}
