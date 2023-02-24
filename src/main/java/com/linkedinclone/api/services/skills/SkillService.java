package com.linkedinclone.api.services.skills;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.dto.skills.*;
import com.linkedinclone.api.exceptions.notfound.SkillNotFoundException;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.skills.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final ClientRepository clientRepository;
    private final SkillMapper skillMapper;
    private final ClientMapper clientMapper;

    public List<SkillDTO> getSkills() {
        return skillRepository
                .findAll()
                .stream()
                .map(skillMapper::toSkillDTO)
                .toList();
    }

    /**
     * Get skill by Id
     * @param id Id of skill
     * @return Skill
     * @throws SkillNotFoundException Skill Not Found
     */
    public SkillDTO getSkillById(Long id) throws SkillNotFoundException {
        return skillMapper.toSkillDTO(findSkillById(id));
    }

    /**
     * Add a new skill
     * @param request Skill Request Body
     * @return Added Skill
     */
    public SkillDTO addSkill(SkillAddRequest request) {
        Skill skill = skillMapper.createSkill(request);
        Date createdDate = new Date();
        skill.setCreatedAt(createdDate);
        skill.setUpdatedAt(createdDate);
        return skillMapper.toSkillDTO(skillRepository.save(skill));
    }

    /**
     * Update skill
     * @param id Id of skill that will be updated
     * @param request Skill Request Body
     * @return Updated Skill
     * @throws SkillNotFoundException Skill Not Found
     */
    public SkillDTO updateById(Long id, SkillUpdateRequest request) throws SkillNotFoundException {
        Skill skill = findSkillById(id);
        skill = skillMapper.updateSkill(request, skill);
        return SkillDTO.fromSkill(skillRepository.save(skill));
    }

    /**
     * Delete Skill by ID
     * @param id Id of skill that will be deleted
     * @throws SkillNotFoundException Skill Not Found
     */
    public void deleteById(Long id) throws SkillNotFoundException {
        ifNotExistsThrowException(id);
        skillRepository.deleteById(id);
    }

    /**
     * Get List of skill Clients
     * @param id Id of skill
     * @return List of Clients
     * @throws SkillNotFoundException Skill Not Found
     */
    public List<ClientDTO> getSkillClients(Long id) throws SkillNotFoundException {
        ifNotExistsThrowException(id);
        return clientRepository
                .findBySkillsId(id)
                .stream()
                .map(clientMapper::toClientDTO)
                .toList();
    }

    private Skill findSkillById(Long id) throws SkillNotFoundException {
        return skillRepository.findById(id).orElseThrow(SkillNotFoundException::new);
    }

    private void ifNotExistsThrowException(Long id) throws SkillNotFoundException {
        if (!skillRepository.existsById(id))
            throw new SkillNotFoundException();
    }
}
