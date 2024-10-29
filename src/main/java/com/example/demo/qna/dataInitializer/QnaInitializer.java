package com.example.demo.qna.dataInitializer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.dto.QnaDTO;
import com.example.demo.qna.repository.QnaRepository;
import com.example.demo.qna.service.QnaService;

@Component
@Order(7)
public class QnaInitializer implements ApplicationRunner {

    @Autowired
    QnaService qnaService;

    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    QnaRepository qnaRepository;
    
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (qnaRepository.count() > 0) {
            return;
        }

        List<Member> members = memberRepository.findAll();

        if (!members.isEmpty()) {
            Member member1 = members.get(0);
            QnaDTO qnaDTO1 = QnaDTO.builder()
                                    .title("문의드립니다.")
                                    .content("게시글 내용 - 일반 문의")
                                    .build();
            qnaService.writeQna(qnaDTO1, member1.getId());

            if (members.size() > 1) {
                Member member2 = members.get(1);
                QnaDTO qnaDTO2 = QnaDTO.builder()
                                        .title("예약 취소 문의")
                                        .content("게시글 내용 - 예약 취소 문의")
                                        .privatePost(true)
                                        .password("1234")
                                        .build();
                qnaService.writeQna(qnaDTO2, member2.getId());
            }

            if (members.size() > 2) {
                Member member3 = members.get(2);
                QnaDTO qnaDTO3 = QnaDTO.builder()
                                        .title("예약 취소 문의")
                                        .content("게시글 내용 - 예약 취소 문의")
                                        .build();
                qnaService.writeQna(qnaDTO3, member3.getId());
            }

            if (members.size() > 3) {
                Member member4 = members.get(3);
                QnaDTO qnaDTO4 = QnaDTO.builder()
                                        .title("예약 문의")
                                        .content("게시글 내용 - 예약 문의")
                                        .privatePost(true)
                                        .password("1234")
                                        .build();
                qnaService.writeQna(qnaDTO4, member4.getId());
            }

            if (members.size() > 4) {
                Member member5 = members.get(4);
                QnaDTO qnaDTO5 = QnaDTO.builder()
                                        .title("지점 문의")
                                        .content("게시글 내용 - 지점 문의")
                                        .build();
                qnaService.writeQna(qnaDTO5, member5.getId());
            }

            if (members.size() > 5) {
                Member member6 = members.get(5);
                QnaDTO qnaDTO6 = QnaDTO.builder()
                                        .title("지점 문의")
                                        .content("게시글 내용 - 지점 문의")
                                        .privatePost(true)
                                        .password("1234")
                                        .build();
                qnaService.writeQna(qnaDTO6, member6.getId());
            }

            if (members.size() > 6) {
                Member member7 = members.get(6);
                QnaDTO qnaDTO7 = QnaDTO.builder()
                                        .title("테마 관련")
                                        .content("게시글 내용 - 테마 문의")
                                        .build();
                qnaService.writeQna(qnaDTO7, member7.getId());
            }

            if (members.size() > 7) {
                Member member8 = members.get(7);
                QnaDTO qnaDTO8 = QnaDTO.builder()
                                        .title("회원 탈퇴 문의")
                                        .content("게시글 내용 - 회원 탈퇴 문의")
                                        .privatePost(true)
                                        .password("1234")
                                        .build();
                qnaService.writeQna(qnaDTO8, member8.getId());
            }

            if (members.size() > 8) {
                Member member9 = members.get(8);
                QnaDTO qnaDTO9 = QnaDTO.builder()
                                        .title("결제 문의")
                                        .content("게시글 내용 - 결제 문의")
                                        .build();
                qnaService.writeQna(qnaDTO9, member9.getId());
            }

            if (members.size() > 9) {
                Member member10 = members.get(9);
                QnaDTO qnaDTO10 = QnaDTO.builder()
                                         .title("취소 문의")
                                         .content("게시글 내용 - 취소 문의")
                                         .privatePost(true)
                                         .password("1234")
                                         .build();
                qnaService.writeQna(qnaDTO10, member10.getId());
            }
        }
    }
}







