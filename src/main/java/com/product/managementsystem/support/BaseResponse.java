package com.product.managementsystem.support;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"status","message"})
@ToString(of = {"status","message"})
@SuperBuilder(toBuilder = true)
public abstract class BaseResponse {
    private int status;
    private String message;
    private ErrorDto error;
}
