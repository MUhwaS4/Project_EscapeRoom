package com.example.demo.reservation.entity;

import com.example.demo.theme.entity.Theme;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "common_reservationtime")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonReservationTime {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no; 
    

    @Column(length = 10)
    String reservationTime;
    
    @ManyToOne()
    @JoinColumn(name = "theme")
    Theme theme; 
    
    @Column(length = 1)
    String useYn;
}