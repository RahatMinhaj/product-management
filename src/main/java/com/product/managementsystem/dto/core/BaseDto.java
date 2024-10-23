package com.product.managementsystem.dto.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@SuperBuilder(toBuilder = true)
public class BaseDto implements Serializable {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
