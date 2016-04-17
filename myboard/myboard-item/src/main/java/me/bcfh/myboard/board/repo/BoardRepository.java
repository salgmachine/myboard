package me.bcfh.myboard.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import me.bcfh.myboard.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

    Board findByName(String name);
}
