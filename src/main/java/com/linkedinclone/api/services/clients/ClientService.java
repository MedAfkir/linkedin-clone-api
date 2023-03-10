package com.linkedinclone.api.services.clients;

import com.linkedinclone.api.dto.users.*;
import com.linkedinclone.api.dto.posts.*;
import com.linkedinclone.api.dto.skills.*;
import com.linkedinclone.api.dto.clients.*;
import com.linkedinclone.api.dto.comments.*;
import com.linkedinclone.api.dto.positions.*;
import com.linkedinclone.api.dto.experiences.*;
import com.linkedinclone.api.exceptions.alreadyused.*;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.models.experiences.*;
import com.linkedinclone.api.models.images.Image;
import com.linkedinclone.api.models.images.ImageRepository;
import com.linkedinclone.api.models.positions.*;
import com.linkedinclone.api.models.comments.*;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.skills.*;
import com.linkedinclone.api.models.posts.*;
import com.linkedinclone.api.services.images.ImageService;
import com.linkedinclone.api.services.images.ImageUploadListener;
import com.linkedinclone.api.utils.FilesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final PasswordEncoder encoder;

    private final ClientMapper clientMapper;
    private final SkillMapper skillMapper;
    private final PositionMapper positionMapper;
    private final ExperienceMapper experienceMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final ClientRepository clientRepository;
    private final SkillRepository skillRepository;
    private final ExperienceRespository experienceRespository;
    private final PostRepository postRepository;
    private final PositionRepository positionRepository;
    private final CommentRepository commentsRepository;
    private final ImageService imageService;

    private final ImageRepository imageRepository;

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toClientDTO)
                .toList();
    }

    public ClientDTO addClient(UserRegistrationRequest request)
            throws UsernameAlreadyUsedException, EmailAlreadyUsedException {
        boolean usernameExists = clientRepository.existsByUsername(request.getUsername());
        if (usernameExists)
            throw new UsernameAlreadyUsedException(request.getUsername());

        boolean emailExists = clientRepository.existsByEmail(request.getEmail());
        if (emailExists)
            throw new EmailAlreadyUsedException(request.getEmail());

        Client client = clientMapper.createClient(request);
        client.setPassword(encoder.encode(request.getPassword()));
        Date creationDate = new Date();
        client.setCreatedAt(creationDate);
        client.setUpdatedAt(creationDate);
        return clientMapper.toClientDTO(clientRepository.save(client));
    }

    public ClientDTO getClientById(Long id) throws ClientNotFoundException {
        return clientMapper.toClientDTO(findClientById(id));
    }

    public void deleteClientById(Long id) throws ClientNotFoundException {
        clientExistsById(id);
        clientRepository.deleteById(id);
    }

    public List<SkillDTO> getClientSkills(Long id) throws ClientNotFoundException {
        clientExistsById(id);
        return skillRepository.findByClientsId(id)
                .stream()
                .map(skillMapper::toSkillDTO)
                .toList();
    }

    public PositionDTO getClientPosition(Long id) throws ClientNotFoundException {
        clientExistsById(id);
        Position position = positionRepository.findByClientsId(id);
        return positionMapper.toPositionDTO(position);
    }

    public List<ExperienceDTO> getClientExperiences(Long id) throws ClientNotFoundException {
        clientExistsById(id);
        return experienceRespository.findByClientId(id)
                .stream()
                .map(experienceMapper::toExperienceDTO)
                .toList();
    }

    public List<PostSummaryDTO> getClientPosts(Long id) throws ClientNotFoundException {
        clientExistsById(id);
        return postRepository.findByClientId(id)
                .stream()
                .map(postMapper::toPostSummaryDTO)
                .toList();
    }

    public List<CommentSummaryDTO> getClientComments(Long id) throws ClientNotFoundException {
        clientExistsById(id);
        return commentsRepository.findByClientId(id)
                .stream()
                .map(commentMapper::toCommentSummaryDTO)
                .toList();
    }

    public Client findClientByUsername(String username) throws ClientNotFoundException {
        return clientRepository
                .findByUsername(username)
                .orElseThrow(ClientNotFoundException::new);
    }

    public Client findClientById(Long id) throws ClientNotFoundException {
        return clientRepository
                .findById(id)
                .orElseThrow(ClientNotFoundException::new);
    }

    private void clientExistsById(Long id) throws ClientNotFoundException {
        if (!clientRepository.existsById(id))
            throw new ClientNotFoundException();
    }

    private void uploadProfilePicture(
            Long clientId,
            MultipartFile multipartFile,
            final ImageUploadListener listener
            ) throws ClientNotFoundException, IOException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
        String name = FilesUtils.generateName();
        String url = imageService.uploadImage(
                FilesUtils.convertToFile(multipartFile),
                name
        );
        Image image = Image.builder()
                .name(name)
                .url(url)
                .build();
        imageRepository.save(image);
        client.setImage(image);
        clientRepository.save(client);
        listener.onSuccess(true);
    }

    public void updateProfilePicture(
            Long clientId,
            MultipartFile multipartFile,
            final ImageUploadListener listener
        ) throws ClientNotFoundException, IOException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
        Image image = client.getImage();
        if(image == null) {
            listener.onSuccess(false);
            return;
        }
        imageService.updateImage(
                FilesUtils.convertToFile(multipartFile),
                image.getName()
        );
        listener.onSuccess(true);
    }

    public void deleteProfilePicture(Long clientId, final ImageUploadListener listener)
            throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
        Image image = client.getImage();
        if(image == null) {
            listener.onSuccess(false);
            return;
        }
        imageService.deleteImage(image.getName());
        listener.onSuccess(true);
    }
}
