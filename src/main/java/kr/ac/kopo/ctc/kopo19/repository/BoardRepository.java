package kr.ac.kopo.ctc.kopo19.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.kopo.ctc.kopo19.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Optional<Board> findByName(String boardName);
    
}
