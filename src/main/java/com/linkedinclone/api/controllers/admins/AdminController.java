package com.linkedinclone.api.controllers.admins;

import com.linkedinclone.api.dto.admins.AdminDTO;
import com.linkedinclone.api.dto.admins.ApprovalRequest;
import com.linkedinclone.api.dto.admins.RoleRequest;
import com.linkedinclone.api.dto.users.UserUpdateRequest;
import com.linkedinclone.api.exceptions.invalidbodyrequest.InvalidBodyRequestException;
import com.linkedinclone.api.exceptions.notfound.AdminNotFoundException;
import com.linkedinclone.api.services.admins.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable("id") Long id)
            throws AdminNotFoundException {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdminById(
            @PathVariable("id") Long id,
            @RequestBody UserUpdateRequest request
    ) throws AdminNotFoundException {
        return ResponseEntity.ok(adminService.updateAdminById(id, request));
    }

    @PutMapping("/{id}/approval")
    public ResponseEntity<?> updateAdminApprovalById(
            @PathVariable("id") Long id,
            @RequestBody ApprovalRequest request
    ) throws AdminNotFoundException, InvalidBodyRequestException {
        adminService.setApprovalAdmin(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateAdminRole(
            @PathVariable("id") Long id,
            @Valid @RequestBody RoleRequest request
    ) throws AdminNotFoundException, InvalidBodyRequestException {
        adminService.setAdminRole(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admins/approved")
    public ResponseEntity<List<AdminDTO>> getApprovedAdmins() {
        return ResponseEntity.ok(adminService.getApprovedAdmins());
    }

    @GetMapping("/admins/not-approved")
    public ResponseEntity<List<AdminDTO>> getNotApprovedAdmins() {
        return ResponseEntity.ok(adminService.getNotApprovedAdmins());
    }

    @GetMapping("/admins/pending")
    public ResponseEntity<List<AdminDTO>> getOnPendingAdmins() {
        return ResponseEntity.ok(adminService.getOnPendingAdmins());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id)
            throws AdminNotFoundException {
        adminService.deleteAdminById(id);
        return ResponseEntity.ok().build();
    }
}
