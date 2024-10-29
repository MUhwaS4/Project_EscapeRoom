package com.example.demo.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.repository.QnaRepository;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.reservation.repository.ReservationRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	QnaRepository qnaRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	// 인코더 필드 선언
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	// 회원 등록
	public boolean register(MemberDTO dto) {
		
		// 회원 정보에서 아이디 꺼내기
		String id = dto.getId();
		
		// 아이디 중복 여부를 확인하고, 회원 등록을 진행
		MemberDTO getDto = read(id);
		
		// 해당 아이디가 사용 중이라면 등록 취소
		if (getDto != null) {
			System.out.println("사용 중인 아이디입니다.");
			return false;
		}
		
		// 해당 아이디가 사용 중이 아니라면 등록 진행
		Member entity = dtoToEntity(dto);
		
		// 인코더로 패스워드를 암호화 한 후 업데이트
		String enPw = passwordEncoder.encode(entity.getPassword());
		
		entity.setPassword(enPw);
		
		// 이후 회원 정보 저장
		memberRepository.save(entity);
		
		return true;
		
	}

	@Override
	// 회원 단건 조회
	public MemberDTO read(String id) {
		
		Optional<Member> result = memberRepository.findById(id);
		
		// 회원 데이터가 존재한다면 내용을 DTO로 변환
		if (result.isPresent()) {
			
			Member member = result.get();
			return entityToDto(member);
			
		}
		
		return null;
		
	}

	@Override
	// 회원 목록 조회
	public Page<MemberDTO> getList(int pageNumber) {

		// 페이지 없이 리스트 조회할 때 함수
//		List<Member> result = memberRepository.findAll();
//		List<MemberDTO> list = new ArrayList<>();
//		
//		list = result.stream()
//					.map(entity -> entityToDto(entity))
//					.collect(Collectors.toList());
		
		// 전달받은 페이지 번호를 인덱스로 변경
		int pageIndex = (pageNumber == 0) ? 0 : pageNumber - 1;
		
		// 정렬 조건 만들기: 가입일을 기준으로 내림차순 정렬
		Sort sort = Sort.by("regDate").descending();
		
		// 페이징 조건 만들기
		Pageable pageable = PageRequest.of(pageIndex, 10, sort);
		
		// 회원 목록 조회
		Page<Member> entityPage = memberRepository.findAll(pageable);
		
		// 엔티티 리스트를 DTO 리스트로 변환
		Page<MemberDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
		
		return dtoPage;
		
	}

	@Override
	// 아이디 중복 조회
	public boolean checkIdExists(String userId) {
		
		Optional<Member> member = memberRepository.findById(userId);
        return member != null; // 존재하면 true, 없으면 false
        
	}

	@Override
	// 회원 정보 수정 메소드
	public void modify(MemberDTO dto) {
		
		// 전달받은 DTO에서 멤버 ID를 꺼내고, DB에 존재하는지 확인
		
		String id = dto.getId();
		
		Optional<Member> optional = memberRepository.findById(id);
		
		// 데이터가 존재할 경우
		if (optional.isPresent()) {
			
			Member member = optional.get();
			
			// 기존 ENTITY에서 비밀번호, 연락처, 이메일, 수신 여부 변경
			member.setPassword(passwordEncoder.encode(dto.getPassword()));
			member.setPhone(dto.getPhone());
			member.setPhoneCk(dto.getPhoneCk());
			member.setEmail(dto.getEmail());
			member.setEmailCk(dto.getEmailCk());
			
			memberRepository.save(member);
			
		}
		
	}

	@Override
	@Transactional
	// 유저 삭제 메소드
	public void remove(String userId) {
		
		// 유저가 존재하는지 확인하고 삭제
		Optional<Member> optional = memberRepository.findById(userId);
		
		if (optional.isPresent()) {
		    
		 // 댓글을 가져와 작성자 ID를 null로 설정
			List<Comment> comments = commentRepository.findById(optional.get());
			
			for (Comment comment : comments) {
				comment.setId(null);  // 댓글의 작성자 ID를 null로 설정
				commentRepository.save(comment);  // 댓글 업데이트
			}
			
			List<Qna> qnas = qnaRepository.findById(optional.get());
			
			for (Qna qna : qnas) {
				qna.setId(null);
				qnaRepository.save(qna);
			}
			
			List<Reservation> reservations = reservationRepository.findByMember(optional.get());
			
			for (Reservation reservation : reservations) {
				reservation.setMember(null);
				reservationRepository.save(reservation);
			}

		    // Member 삭제
			memberRepository.deleteById(userId);
			
		}
		
	}

}
