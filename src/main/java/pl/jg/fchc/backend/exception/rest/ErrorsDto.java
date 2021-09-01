package pl.jg.fchc.backend.exception.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsDto<ErrorDto> {

    private List<ErrorDto> errors;

}
