package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
