package com.example.board.repository;

import com.example.board.model.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void saveMember(Member member);
    Member findMember(String member_id);
}
