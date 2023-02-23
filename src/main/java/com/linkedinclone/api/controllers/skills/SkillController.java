package com.linkedinclone.api.controllers.skills;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.skills.SkillAddRequest;
import com.linkedinclone.api.dto.skills.SkillDTO;
import com.linkedinclone.api.dto.skills.SkillUpdateRequest;
import com.linkedinclone.api.exceptions.notfound.SkillNotFoundException;
import com.linkedinclone.api.services.skills.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable("id") Long id)
            throws SkillNotFoundException {
        SkillDTO skillDTO = skillService.getSkillById(id);
        return ResponseEntity.ok(skillDTO);
    }

    @PostMapping
    public ResponseEntity<SkillDTO> addSkill(@RequestBody SkillAddRequest request) {
        return ResponseEntity.ok(skillService.addSkill(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable(name = "id") Long id,
            @RequestBody SkillUpdateRequest request
    ) throws SkillNotFoundException {
        SkillDTO skillDTO = skillService.updateById(id, request);
        return ResponseEntity.ok(skillDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(
            @PathVariable(name = "id") Long id
    ) throws SkillNotFoundException {
        skillService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<ClientDTO>> getSkillClients(
            @PathVariable(name = "id") Long id
    ) throws SkillNotFoundException {
        List<ClientDTO> clients = skillService.getSkillClients(id);
        return ResponseEntity.ok(clients);
    }
}
