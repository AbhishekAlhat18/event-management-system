package com.project.event_management_system.repository;

import com.project.event_management_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    //	List<Event> findEventByName(String eventName);
    @Query(value = "select * from event_info where organizer_id =?1", nativeQuery=true)
    public List<Event> getByOrganizerId(int id);

}
