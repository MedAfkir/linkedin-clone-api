package com.linkedinclone.api.services.experiences;

import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.experiences.*;
import com.linkedinclone.api.dto.experiences.*;
import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.ExperienceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRespository experienceRespository;
    private final ClientRepository clientRepository;

    private final ExperienceMapper experienceMapper;
    private final ClientMapper clientMapper;

    public List<ExperienceDTO> getAllExperiences() {
        return experienceMapper.toExperienceDTO(experienceRespository.findAll());
    }

    public ExperienceWithClientDTO getExperienceById(Long id) throws ExperienceNotFoundException {
        Experience experience = findExperienceById(id);
        return experienceMapper.toExperienceWithClientDTO(experience);
    }

    /**
     * Add experience
     *
     * @param request Experience Request Body
     * @return Experience added
     * @throws ClientNotFoundException Client Not Found
     */
    public ExperienceWithClientDTO addExperience(ExperienceRequest request) throws ClientNotFoundException {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(ClientNotFoundException::new);
        Experience experience = experienceMapper.createExperience(request);
        experience.setClient(client);
        return experienceMapper.toExperienceWithClientDTO(experience);
    }

    /**
     * Update an experience by its ID
     *
     * @param id      ID of experience that will be updated
     * @param request Experience Request Body
     * @return Experience updated
     * @throws ClientNotFoundException     Client Not Found
     * @throws ExperienceNotFoundException Experience Not Found
     */
    public ExperienceWithClientDTO updateExperience(Long id, ExperienceRequest request) throws ExperienceNotFoundException, ClientNotFoundException {
        Experience experience = findExperienceById(id);

        if (!experience.getClient().getId().equals(request.getClientId())) {
            Client client = clientRepository
                    .findById(request.getClientId())
                    .orElseThrow(ClientNotFoundException::new);
            experience.setClient(client);
        }

        experience = experienceMapper.updateExperience(request, experience);
        return experienceMapper.toExperienceWithClientDTO(experienceRespository.save(experience));
    }

    /**
     * Delete Experience
     *
     * @param id Id of experience that will be deleted
     * @throws ExperienceNotFoundException Experience Not Found
     */
    public void deleteExperienceById(Long id) throws ExperienceNotFoundException {
        ifNotExistsThrowException(id);
        experienceRespository.deleteById(id);
    }

    /**
     * Get the owner of an experience
     *
     * @param id Id of experience whose client will be fetched
     * @return Experience Owner
     * @throws ExperienceNotFoundException Experience Not Found
     */
    public ClientDTO getExperienceClient(Long id) throws ExperienceNotFoundException {
        ifNotExistsThrowException(id);
        return clientMapper.toClientDTO(clientRepository.findByExperiencesId(id));
    }

    private Experience findExperienceById(Long id) throws ExperienceNotFoundException {
        return experienceRespository
                .findById(id)
                .orElseThrow(ExperienceNotFoundException::new);
    }

    private void ifNotExistsThrowException(Long id) throws ExperienceNotFoundException {
        if (!experienceRespository.existsById(id))
            throw new ExperienceNotFoundException();
    }
}
