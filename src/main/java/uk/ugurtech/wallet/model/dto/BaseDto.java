package uk.ugurtech.wallet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Boolean isActive;
    private Boolean isDeleted;
}
