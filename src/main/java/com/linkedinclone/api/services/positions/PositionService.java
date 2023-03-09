package com.linkedinclone.api.services.positions;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.exceptions.notfound.PositionNotFoundException;
import com.linkedinclone.api.dto.positions.*;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.positions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final ClientRepository clientRepository;

    private final PositionMapper positionMapper;
    private final ClientMapper clientMapper;

    /**
     * Get all positions
     * @return List of positions
     */
    public List<PositionDTO> getAllPositions() {
        return positionRepository
                .findAll()
                .stream()
                .map(positionMapper::toPositionDTO)
                .toList();
    }

    /**
     * Get Position By Id
     * @param id Id of position that will be retrieved
     * @return Position
     * @throws PositionNotFoundException Position Not Found
     */
    public PositionDTO getPositionById(Long id) throws PositionNotFoundException {
        return positionMapper.toPositionDTO(findPositionById(id));
    }

    /**
     * Add position
     * @param request Position Request Body
     * @return Added Position
     */
    public PositionDTO addPosition(PositionRequest request) {
        Position position = positionMapper.createPosition(request);
        Date createdDate = new Date();
        position.setCreatedAt(createdDate);
        position.setUpdatedAt(createdDate);
        return positionMapper.toPositionDTO(positionRepository.save(position));
    }

    /**
     * Update a position By id
     * @param id Id of position that will be updated
     * @param request Position Request Body
     * @return Position updated
     * @throws PositionNotFoundException Position Not Found
     */
    public PositionDTO updatePosition(Long id, PositionRequest request) throws PositionNotFoundException {
        Position position = findPositionById(id);
        position = positionMapper.updatePosition(request, position);
        return positionMapper.toPositionDTO(positionRepository.save(position));
    }

    /**
     * Delete a position by Id
     * @param id Id of position that will be deleted
     * @throws PositionNotFoundException Position not found
     */
    public void deletePosition(Long id) throws PositionNotFoundException {
        notExistsByIdThrowException(id);
        positionRepository.deleteById(id);
    }

    /**
     * Get Positions clients
     * @param id Id of position whose clients will be retrieved
     * @return List of clients
     * @throws PositionNotFoundException Position not found
     */
    public List<ClientDTO> getPositionClients(Long id) throws PositionNotFoundException {
        notExistsByIdThrowException(id);
        return clientRepository
                .findByPositionId(id)
                .stream()
                .map(clientMapper::toClientDTO)
                .toList();
    }

    private Position findPositionById(Long id) throws PositionNotFoundException {
        return positionRepository
                .findById(id)
                .orElseThrow(PositionNotFoundException::new);
    }

    private void notExistsByIdThrowException(Long id) throws PositionNotFoundException {
        if (!positionRepository.existsById(id))
            throw new PositionNotFoundException();
    }
}
