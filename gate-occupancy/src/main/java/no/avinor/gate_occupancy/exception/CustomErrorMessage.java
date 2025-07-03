package no.avinor.gate_occupancy.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomErrorMessage {

  LOCATION_NOT_FOUND             (404, "Location not found."),
  AIRPORT_NOT_FOUND             (404, "Airport not found."),
  ZONE_NOT_FOUND             (404, "Zone not found."),
  TERMINAL_NOT_FOUND             (404, "Terminal not found."),

  // --- Location Errors ---
  LOCATION_ALREADY_EXISTS    (409, "Location already exists."),

  // --- ... Errors ---
  INVALID___DATA          (400, "... data is invalid."),

  // ---  errors ---
  LOCATION_NOT_IN_ZONE(404, "Location is not in zone"),

  // --- Authorization ---
  UNAUTHORIZED_OPERATION     (403, "You are not authorized to perform this operation."),

  // --- Generic ---
  INTERNAL_SERVER_ERROR      (500, "An unexpected internal server error occurred.");


  private final int status;
  private final String message;
}
