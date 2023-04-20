package com.example.board.model.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class BoardUpdateForm {
    private Long board_id;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    private String member_id;
    private Long hit;
    private LocalDateTime created_time;
    private boolean isFileRemoved;
}
