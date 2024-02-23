package edu.ifri.smartclass.payload.request;

import jakarta.validation.constraints.NotNull;

public record UserUpdate (@NotNull
                          String firstName,
                          @NotNull
                          String lastName)
{

}
