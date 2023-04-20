package com.example.board.model.board;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reply {
	private Long reply_id;		// 리플 아이디(일련번호)
	private Long board_id;		// 게시글 아이디
	private String member_id;	// 작성자 아이디
	private String content;		// 리플 내용
	private LocalDateTime created_time;	// 등록시간
	
	public static ReplyDto toDto(Reply reply) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setReply_id(reply.getReply_id());
        replyDto.setBoard_id(reply.getBoard_id());
        replyDto.setMember_id(reply.getMember_id());
        replyDto.setContent(reply.getContent());
        replyDto.setCreated_time(reply.getCreated_time());

        return replyDto;
    }
}	
