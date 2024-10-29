package com.example.demo.reservation.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.member.entity.Member;
import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "RESERVATION")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no; // 예약번호

    @ManyToOne// cascade : 연관된 엔티티도 함께 영속 상태로 전이되는 것 , 영속전의 어노테이션
    Member member; // 예약자정보

    @ManyToOne()
    Theme theme; // 테마명

    @ManyToOne()
    Spot spotName; // 지점이름

    @Column(nullable = false)
    LocalDate reservationDate; // 예약 날짜 (LocalDate 타입으로 수정)

    @Column(length = 4, nullable = false)
    String reservationTime; // 예약 시간 (LocalDateTime 타입으로 수정)

    @Column(nullable = false)
    int people; // 인원

    @Column(nullable = false)
    int amount; // 결제 금액

    @Column(length = 10)
    String status; // 결제 상태
    
    
}