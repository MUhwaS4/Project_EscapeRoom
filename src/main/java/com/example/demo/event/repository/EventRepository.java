package com.example.demo.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, String> {

}
