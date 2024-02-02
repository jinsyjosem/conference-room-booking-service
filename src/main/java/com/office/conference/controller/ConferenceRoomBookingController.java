package com.office.conference.controller;

import com.office.conference.dto.request.ConferenceRoomBookingRequestDto;
import com.office.conference.dto.request.ConferenceRoomSearchRequestDto;
import com.office.conference.dto.response.ConferenceRoomBookingResponseDto;
import com.office.conference.dto.response.ConferenceRoomSearchResponseDto;

import com.office.conference.service.ConferenceRoomAvailabilityService;
import com.office.conference.service.ConferenceRoomBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Conference room search and booking apis" )
@Slf4j
public class ConferenceRoomBookingController {

    @Autowired
    private ConferenceRoomBookingService conferenceRoomBookingService;

    @Autowired
    private ConferenceRoomAvailabilityService conferenceRoomAvailabilityService;

    @Operation(summary = "Get available conference rooms list")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Response with success"),
            @ApiResponse(responseCode = "500", description = "Something went wrong on server's side") })
    @PostMapping("/rooms")
    public ResponseEntity<List<ConferenceRoomSearchResponseDto>> findAllAvailableRooms(@RequestBody ConferenceRoomSearchRequestDto conferenceRoomSearchRequestDto){
        log.info("Inside findAllAvailableRooms method");
        List<ConferenceRoomSearchResponseDto> rooms = conferenceRoomAvailabilityService.checkAvailableRooms(conferenceRoomSearchRequestDto.getStartTime(),
                conferenceRoomSearchRequestDto.getEndTime());
        return ResponseEntity.ok().body(rooms);
    }


    @Operation(summary = "Get Booked conference room details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Response with success"),
            @ApiResponse(responseCode = "500", description = "Something went wrong on server's side") })
    @PostMapping("/rooms/book")
    public ResponseEntity<ConferenceRoomBookingResponseDto> BookRoom(@RequestBody @Valid ConferenceRoomBookingRequestDto conferenceRoomBookingRequestDto){
        log.info("Inside BookRoom method");
        ConferenceRoomBookingResponseDto responseDto = conferenceRoomBookingService.bookRoom(conferenceRoomBookingRequestDto.getCount(),
                conferenceRoomBookingRequestDto.getStartTime(),conferenceRoomBookingRequestDto.getEndTime());
        return ResponseEntity.ok().body(responseDto);
    }


}
