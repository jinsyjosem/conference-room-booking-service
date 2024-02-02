package com.office.conference.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConferenceRoomNotFoundException.class })
    public ResponseEntity<Object> handleConferenceRoomNotFoundException(ConferenceRoomNotFoundException ex) {
        log.error("Handling ConferenceRoomNotFoundException ");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler({ DurationNotMultipleOf15MinutesException.class })
    public ResponseEntity<Object> handleDurationNotMultipleOf15MinutesException(DurationNotMultipleOf15MinutesException ex) {
        log.error("Handling DurationNotMultipleOf15MinutesException ");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler({ ConferenceRoomMaintenanceException.class })
    public ResponseEntity<Object> handleConferenceRoomMaintenanceException(ConferenceRoomMaintenanceException ex) {
        log.error("Handling ConferenceRoomMaintenanceException ");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler({Exception.class })
    public ResponseEntity<Object> handleDefaultException(Exception ex) {
        log.error("Handling default Exception ");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "We are facing exception in the system, please try later");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
