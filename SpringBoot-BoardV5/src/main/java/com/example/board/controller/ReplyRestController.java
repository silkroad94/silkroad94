package com.example.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.board.model.board.Reply;
import com.example.board.model.board.ReplyDto;
import com.example.board.model.member.Member;
import com.example.board.repository.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("reply")
@RestController
public class ReplyRestController {
	
	private final ReplyMapper replyMapper;
	
	// 리플 등록
	@PostMapping("{board_id}")
	public ResponseEntity<String> writeReply(@PathVariable Long board_id,
											 @ModelAttribute Reply reply,
											 @SessionAttribute("loginMember") Member loginMember) {
		log.info("reply: {}", reply);
		reply.setBoard_id(board_id);
		reply.setMember_id(loginMember.getMember_id());
		
		replyMapper.saveReply(reply);
		
		return ResponseEntity.ok("등록 성공");
	}
	
	// 리플 읽기 (/reply/게시물아이디/리플아이디)
	@GetMapping("{board_id}/{reply_id}")
	public ResponseEntity<Reply> findReply(@PathVariable Long board_id,
										   @PathVariable Long reply_id) {
		Reply reply = null;
		return ResponseEntity.ok(reply);
	}
	
	// 리플 목록
	@GetMapping("{board_id}")
	public ResponseEntity<List<ReplyDto>> findReplies(@SessionAttribute("loginMember") Member loginMember,
												   	  @PathVariable Long board_id) {
		List<Reply> replies = replyMapper.findReplies(board_id);
		List<ReplyDto> replyDtos = new ArrayList<>();
        if (replies != null && replies.size() > 0) {
            for (Reply reply : replies) {
                ReplyDto replyDto = Reply.toDto(reply);
                if (reply.getMember_id().equals(loginMember.getMember_id())) {
                    replyDto.setWriter(true);
                }
                replyDtos.add(replyDto);
            }
        }
		
		return ResponseEntity.ok(replyDtos);
	}
	
	// 리플 수정
	@PutMapping("{board_id}/{reply_id}")
	public ResponseEntity<Reply> updateReply(@SessionAttribute("loginMember") Member loginMember,
											 @PathVariable Long board_id,
											 @PathVariable Long reply_id,
											 @ModelAttribute Reply reply) {
		
		// 수정 권한 확인
        Reply findReply = replyMapper.findReply(reply_id);
        if (findReply.getMember_id().equals(loginMember.getMember_id())) {
            replyMapper.updateReply(reply);
        }
		
		return ResponseEntity.ok(reply);
	}
	
	// 리플 삭제
	@DeleteMapping("{board_id}/{reply_id}")
	public ResponseEntity<String> removeReply(@SessionAttribute("loginMember") Member loginMember,
											  @PathVariable Long board_id,
											  @PathVariable Long reply_id) {
		 // 삭제 권한 확인
        Reply findReply = replyMapper.findReply(reply_id);
        if (findReply.getMember_id().equals(loginMember.getMember_id())) {
            replyMapper.removeReply(reply_id);
        }
		return ResponseEntity.ok("삭제 성공");
	}
}










