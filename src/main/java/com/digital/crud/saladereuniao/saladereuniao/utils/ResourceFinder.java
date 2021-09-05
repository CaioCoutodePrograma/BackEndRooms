package com.digital.crud.saladereuniao.saladereuniao.utils;

import com.digital.crud.saladereuniao.saladereuniao.exceptions.ResourceNotFoundException;
import com.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;

public class ResourceFinder {

    public void findResourceRoom(RoomRepository roomRepository , long id) throws ResourceNotFoundException {
        roomRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Romm not found for this id::"+id));
    }
}
