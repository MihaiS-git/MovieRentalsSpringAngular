package ro.ubb.rentals.web.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class BaseDto implements Serializable {
    private Long id;
}
