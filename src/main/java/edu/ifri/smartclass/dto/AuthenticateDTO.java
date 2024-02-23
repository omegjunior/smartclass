package edu.ifri.smartclass.dto;

import java.util.UUID;

public record AuthenticateDTO(UUID id, String username, String firstName, String lastName, String email, String jwtToken) {
}
