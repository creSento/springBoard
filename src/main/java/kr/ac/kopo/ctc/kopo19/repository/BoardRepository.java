package kr.ac.kopo.ctc.kopo19.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import kr.ac.kopo.ctc.kopo19.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>, JpaSpecificationExecutor<Board> {

    Optional<Board> findByName(String boardName);
    
}
