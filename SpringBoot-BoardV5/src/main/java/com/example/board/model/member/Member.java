package com.example.board.model.member;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Member {
    private String member_id;
    private String password;
    private String name;
    private GenderType gender;
    private LocalDate birth;
    private String email;
}
