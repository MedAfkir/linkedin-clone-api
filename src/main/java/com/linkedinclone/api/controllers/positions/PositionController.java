package com.linkedinclone.api.controllers.positions;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.positions.PositionDTO;
import com.linkedinclone.api.dto.positions.PositionRequest;
import com.linkedinclone.api.exceptions.notfound.PositionNotFoundException;
import com.linkedinclone.api.services.positions.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> getPositionById(@PathVariable("id") Long id)
            throws PositionNotFoundException {
        return ResponseEntity.ok(positionService.getPositionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDTO> updatePositionById(
            @PathVariable("id") Long id,
            @RequestBody PositionRequest request)
            throws PositionNotFoundException {
        PositionDTO position = positionService.updatePosition(id, request);
        return ResponseEntity.ok(position);
    }

    @PostMapping
    public ResponseEntity<PositionDTO> addPosition(@RequestBody PositionRequest request) {
        return ResponseEntity.ok(positionService.addPosition(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable("id") Long id)
            throws PositionNotFoundException {
        positionService.deletePosition(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<ClientDTO>> getPositionClients(@PathVariable("id") Long id)
            throws PositionNotFoundException {
        return ResponseEntity.ok(positionService.getPositionClients(id));
    }
}
