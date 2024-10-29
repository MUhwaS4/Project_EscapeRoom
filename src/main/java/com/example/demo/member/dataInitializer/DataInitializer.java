package com.example.demo.member.dataInitializer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

@Component
@Order(1)
public class DataInitializer implements ApplicationRunner {
	
	private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 추가

    public DataInitializer(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder; // PasswordEncoder 주입
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memberRepository.save(new Member("test", passwordEncoder.encode("1234"), "테스트", "001118", "010-0000-0000", "동의", "aa@naver.com", "동의", "ROLE_ADMIN"));
		memberRepository.save(new Member("user1", passwordEncoder.encode("1234"), "다프네", "001118", "010-0000-0000", null, "bb@gmail.com", "동의", "ROLE_ADMIN"));
		memberRepository.save(new Member("user2", passwordEncoder.encode("1234"), "프시케", "001118", "010-0000-0000", "동의", "cc@naver.com", null, "ROLE_ADMIN"));
		memberRepository.save(new Member("user3", passwordEncoder.encode("1234"), "짱구", "001118", "010-0000-0000", "동의", "dd@gmail.com", "동의", "ROLE_ADMIN"));
		memberRepository.save(new Member("user4", passwordEncoder.encode("1234"), "짱아", "001118", "010-0000-0000", "동의", "ee@naver.com", null, "ROLE_ADMIN"));
		memberRepository.save(new Member("user5", passwordEncoder.encode("1234"), "흰둥이", "001118", "010-0000-0000", "동의", "ff@gmail.com", "동의", "ROLE_ADMIN"));
		memberRepository.save(new Member("user6", passwordEncoder.encode("1234"), "둘리", "001118", "010-0000-0000", "동의", "rr@gmail.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user7", passwordEncoder.encode("1234"), "또치", "001118", "010-0000-0000", null, null, null, "ROLE_USER"));
		memberRepository.save(new Member("user8", passwordEncoder.encode("1234"), "단비", "001118", "010-0000-0000", "동의", "ss@naver.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user9", passwordEncoder.encode("1234"), "미소", "001118", "010-0000-0000", null, "tt@gmail.com", null, "ROLE_USER"));
		memberRepository.save(new Member("user10", passwordEncoder.encode("1234"), "다다다", "001118", "010-0000-0000", "동의", "gg@gmail.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user11", passwordEncoder.encode("1234"), "아리", "001118", "010-0000-0000", null, null, null, "ROLE_USER"));
		memberRepository.save(new Member("user12", passwordEncoder.encode("1234"), "동동", "001118", "010-0000-0000", null, null, null, "ROLE_USER"));
		memberRepository.save(new Member("user13", passwordEncoder.encode("1234"), "한울", "001118", "010-0000-0000", "동의", "fr@gmail.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user14", passwordEncoder.encode("1234"), "선미", "001118", "010-0000-0000", "동의", "tr@gmail.com", null, "ROLE_USER"));
		memberRepository.save(new Member("user15", passwordEncoder.encode("1234"), "윤지", "001118", "010-0000-0000", null, "qw@naver.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user16", passwordEncoder.encode("1234"), "지아", "001118", "010-0000-0000", null, null, null, "ROLE_USER"));
		memberRepository.save(new Member("user17", passwordEncoder.encode("1234"), "너구리", "001118", "010-0000-0000", null, "te@naver.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user18", passwordEncoder.encode("1234"), "쥐", "001118", "010-0000-0000", "동의", "gg@naver.com", null, "ROLE_USER"));
		memberRepository.save(new Member("user19", passwordEncoder.encode("1234"), "햄스터", "001118", "010-0000-0000", null, "ddddsd@naver.com", "동의", "ROLE_USER"));
		memberRepository.save(new Member("user20", passwordEncoder.encode("1234"), "강아지", "001118", "010-0000-0000", null, "gtesd@naver.com", null, "ROLE_USER"));
		memberRepository.save(new Member("user21", passwordEncoder.encode("1234"), "고양이", "001118", "010-0000-0000", "동의", "xx46577@gmail.com", null, "ROLE_USER"));
		memberRepository.save(new Member("user22", passwordEncoder.encode("1234"), "이름", "001118", "010-0000-0000", null, null, null, "ROLE_USER"));
		
	}

}
