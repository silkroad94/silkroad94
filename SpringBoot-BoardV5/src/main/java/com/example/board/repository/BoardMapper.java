package com.example.board.repository;

import com.example.board.model.board.AttachedFile;
import com.example.board.model.board.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 저장
    void saveBoard(Board board);
    // 전체 게시글 갯수
    int getTotal(String searchText);
    // 전체 게시글 검색
    List<Board> findBoards(String searchText, RowBounds rowBounds);
    // 게시글 아이디로 검색
    Board findBoard(Long board_id);
    // 게시글 수정
    void updateBoard(Board updateBoard);
    // 게시글 삭제
    void removeBoard(Long board_id);
    // 첨부파일 저장
    void saveFile(AttachedFile attachedFile);
    // 게시글 아이디로 첨부파일 검색
    AttachedFile findFileByBoardId(Long board_id);
    // 첨부파일 아이디로 첨부파일 검색
    AttachedFile findFileByAttachedFileId(Long attachedFile_id);
    // 첨부파일 삭제
    void removeAttachedFile(Long attachedFile_id);

}
