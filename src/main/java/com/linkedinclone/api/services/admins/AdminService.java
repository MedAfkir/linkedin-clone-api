package com.linkedinclone.api.services.admins;

import com.linkedinclone.api.dto.users.*;
import com.linkedinclone.api.dto.admins.*;
import com.linkedinclone.api.exceptions.alreadyused.EmailAlreadyUsedException;
import com.linkedinclone.api.exceptions.alreadyused.UsernameAlreadyUsedException;
import com.linkedinclone.api.exceptions.invalidbodyrequest.InvalidBodyRequestException;
import com.linkedinclone.api.exceptions.notfound.AdminNotFoundException;
import com.linkedinclone.api.models.admins.*;
import com.linkedinclone.api.models.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder encoder;

    /**
     * Get all admins
     * @return List of admins
     */
    public List<AdminDTO> getAllAdmins() {
        return adminMapper.toAdminsDTOs(adminRepository.findAll());
    }

    /**
     * Get admin by its ID
     * @param id Id of admin that will be retrieved
     * @return Admin
     * @throws AdminNotFoundException Admin Not Found
     */
    public AdminDTO getAdminById(Long id) throws AdminNotFoundException {
        return adminMapper.toAdminDTO(findAdminById(id));
    }

    /**
     * Add admin
     * @param request Admin Request Body
     * @return Admin added
     */
    public AdminDTO addAdmin(UserRegistrationRequest request)
            throws UsernameAlreadyUsedException, EmailAlreadyUsedException {
        boolean usernameExists = adminRepository.existsByUsername(request.getUsername());
        if (usernameExists)
            throw new UsernameAlreadyUsedException(request.getUsername());

        boolean emailExists = adminRepository.existsByEmail(request.getEmail());
        if (emailExists)
            throw new EmailAlreadyUsedException(request.getEmail());

        Admin admin = adminMapper.createAdmin(request);
        admin.setPassword(encoder.encode(request.getPassword()));
        admin.setApproval(Admin.Approval.PENDING);
        admin.setRole(Role.ADMIN);
        Date creationDate = new Date();
        admin.setCreatedAt(creationDate);
        admin.setUpdatedAt(creationDate);

        return adminMapper.toAdminDTO(
                adminRepository.save(admin)
        );
    }

    /**
     * Update Admin infos
     * @param id Id of admin that will be updated
     * @param request Admin Request Body
     * @return Admin Updated
     * @throws AdminNotFoundException Admin not Found
     */
    public AdminDTO updateAdminById(Long id, UserUpdateRequest request)
            throws AdminNotFoundException {
        Admin admin = findAdminById(id);
        admin = adminMapper.updateAdmin(request, admin);
        return adminMapper.toAdminDTO(adminRepository.save(admin));
    }

    /**
     * Set Approval Admin
     * @param id Id of the admin that will be updated
     * @param request Approval Body Request
     * @throws InvalidBodyRequestException Invalid Request Body
     * @throws AdminNotFoundException Admin Not Found
     */
    public void setApprovalAdmin(Long id, ApprovalRequest request)
            throws InvalidBodyRequestException, AdminNotFoundException {
        Admin admin = findAdminById(id);
        Admin.Approval approval = request.approval();

        if (approval.isPending())
            throw new InvalidBodyRequestException("Approval Admin cannot be set to ON PENDING state");

        admin.setApproval(approval);
        adminRepository.save(admin);
    }

    /**
     * Update Admin Role
     * @param id Id of admin whose role will be updated
     * @param request Request Body contains the <b>new role</b>
     * @throws AdminNotFoundException Admin Not Found
     * @throws InvalidBodyRequestException Request Body invalid
     */
    public void setAdminRole(Long id, RoleRequest request)
            throws AdminNotFoundException, InvalidBodyRequestException {
        Admin admin = findAdminById(id);

        if (!Role.getAdminRoles().contains(request.role()))
            throw new InvalidBodyRequestException("Invalid Admin Role");

        admin.setRole(request.role());
        adminRepository.save(admin);
    }

    /**
     * Delete Admin by its ID
     * @param id Id of admin that will be deleted
     * @throws AdminNotFoundException Admin not Found
     */
    public void deleteAdminById(Long id) throws AdminNotFoundException {
        ifNotExistsThrowException(id);
        adminRepository.deleteById(id);
    }

    /**
     * Get non-approved admins
     * @return List of approved
     */
    public List<AdminDTO> getNotApprovedAdmins() {
        return adminRepository
                .findByApproval(Admin.Approval.NOT_APPROVED)
                .stream()
                .map(adminMapper::toAdminDTO)
                .toList();
    }

    /**
     * Get approved admins
     * @return List of admins on pending
     */
    public List<AdminDTO> getApprovedAdmins() {
        return adminRepository
                .findByApproval(Admin.Approval.APPROVED)
                .stream()
                .map(adminMapper::toAdminDTO)
                .toList();
    }

    /**
     * Get admins on pending
     * @return List of admins on pending
     */
    public List<AdminDTO> getOnPendingAdmins() {
        return adminMapper.toAdminsDTOs(
                adminRepository.findByApproval(Admin.Approval.PENDING)
        );
    }

    public Admin findAdminByUsername(String username) throws AdminNotFoundException {
        return adminRepository
                .findByUsername(username)
                .orElseThrow(AdminNotFoundException::new);
    }

    /**
     * Find Admin by its Id
     * @param id Id of the admin that will be retrieved
     * @return Admin found
     * @throws AdminNotFoundException Admin Not Found
     */
    private Admin findAdminById(Long id) throws AdminNotFoundException {
        return adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
    }

    private void ifNotExistsThrowException(Long id) throws AdminNotFoundException {
        if (!adminRepository.existsById(id))
            throw new AdminNotFoundException();
    }
}
