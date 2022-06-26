package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryVOTest {

    MemberRepositoryVO repositoryVO = new MemberRepositoryVO();


    @Test
    void crud() throws SQLException {
        Member member = new Member("memberVO6", 1000);
        repositoryVO.save(member);


        //findById
        Member findMember = repositoryVO.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);


        //update : money : 10000 -> 20000
        repositoryVO.update(member.getMemberId(), 20000);
        Member updateMember = repositoryVO.findById(member.getMemberId());
        Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

        //delete
        repositoryVO.delete(member.getMemberId());

        Assertions.assertThatThrownBy(() -> repositoryVO.findById(member.getMemberId()))
                        .isInstanceOf(NoSuchElementException.class);

    }
}