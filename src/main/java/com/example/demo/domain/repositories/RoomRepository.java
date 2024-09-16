package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    @Query("SELECT r FROM Room r " +
        "WHERE (:cityId IS NULL OR r.hotel.city.id = :cityId) " +
        "AND (:capacity IS NULL OR r.capacity >= :capacity) " +
        "AND NOT EXISTS (SELECT res FROM Reservation res " +
        "WHERE res.room = r " +
        "AND ((:startDate IS NOT NULL AND res.endDate >= :startDate) " +
        "AND (:endDate IS NOT NULL AND res.startDate <= :endDate)))")
    List<Room> findAvailableRooms(@Param("cityId") Long cityId,
                                  @Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate,
                                  @Param("capacity") Integer capacity);
}
