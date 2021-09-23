package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.admin.Admin;
import za.ac.nwu.ac.domain.dto.gameboard.GameBoard;

public interface GameBoardRepository extends JpaRepository<GameBoard, Long> {
}
