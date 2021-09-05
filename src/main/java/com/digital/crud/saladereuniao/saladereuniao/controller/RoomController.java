package com.digital.crud.saladereuniao.saladereuniao.controller;

import com.digital.crud.saladereuniao.saladereuniao.exceptions.ResourceNotFoundException;
import com.digital.crud.saladereuniao.saladereuniao.model.Room;
import com.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import com.digital.crud.saladereuniao.saladereuniao.utils.ResourceFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    private ResourceFinder resourceFinder = new ResourceFinder();

    @GetMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping(value = "/rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> findById(@PathVariable(value = "id") long idRoom) throws ResourceNotFoundException {
        Room fundedRoom = roomRepository.findById(idRoom).orElseThrow(() -> new ResourceNotFoundException("Room not found" + idRoom));
        return ResponseEntity.ok(fundedRoom);
    }

    @PostMapping(value = "/rooms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room createdRoom) {
        return ResponseEntity.ok(roomRepository.save(createdRoom));
    }

    @PutMapping(value = "/rooms/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room updatedRoom, @PathVariable(value = "id") long updatedRoomId) throws ResourceNotFoundException {
        resourceFinder.findResourceRoom(roomRepository, updatedRoomId);
        updatedRoom.setId(updatedRoomId);
        return ResponseEntity.ok(roomRepository.save(updatedRoom));
    }

    @DeleteMapping(value = "/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") long roomIdtoDelete) throws ResourceNotFoundException {
        resourceFinder.findResourceRoom(roomRepository, roomIdtoDelete);
        roomRepository.deleteById(roomIdtoDelete);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
