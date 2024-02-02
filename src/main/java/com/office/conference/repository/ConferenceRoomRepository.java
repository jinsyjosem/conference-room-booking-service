package com.office.conference.repository;

import com.office.conference.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    @Query(value = "SELECT * FROM Conference_Room WHERE id NOT IN (SELECT room_id FROM meeting WHERE " +
            "start_Time BETWEEN :startTime AND :endTime or end_Time BETWEEN :startTime AND :endTime) order by capacity", nativeQuery = true)
    List<ConferenceRoom> findAvailableMeetingRooms(LocalTime startTime, LocalTime endTime);

    @Query(value = "SELECT * FROM Conference_Room WHERE capacity >= :capacity and id NOT IN (SELECT room_id FROM meeting WHERE " +
            "start_Time BETWEEN :startTime AND :endTime or end_Time BETWEEN :startTime AND :endTime) order by capacity", nativeQuery = true)
    List<ConferenceRoom> findAvailableMeetingRoomsBasedOnCapacity(LocalTime startTime, LocalTime endTime,int capacity);
}
