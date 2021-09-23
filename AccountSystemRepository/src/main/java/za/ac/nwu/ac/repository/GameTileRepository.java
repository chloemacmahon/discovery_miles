package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.admin.Admin;
import za.ac.nwu.ac.domain.dto.gameboard.GameTile;

public interface GameTileRepository extends JpaRepository<GameTile, Long> {
}
