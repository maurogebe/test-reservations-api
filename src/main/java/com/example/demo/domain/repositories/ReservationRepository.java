package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    @Query("SELECT r FROM Reservation r WHERE (r.startDate < :endDate AND r.endDate > :startDate)")
    List<Reservation> findConflictingReservations(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT r FROM Reservation r WHERE " +
        "(:customerId IS NULL OR r.customer.id = :customerId) AND " +
        "(:roomId IS NULL OR r.room.id = :roomId) AND " +
        "(:startDate IS NULL OR r.startDate >= :startDate) AND " +
        "(:endDate IS NULL OR r.endDate <= :endDate)")
    List<Reservation> findReservations(@Param("customerId") Long customerId, @Param("roomId") Long roomId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

