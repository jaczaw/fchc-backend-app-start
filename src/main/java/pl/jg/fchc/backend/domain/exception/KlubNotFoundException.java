package pl.jg.fchc.backend.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KlubNotFoundException extends RuntimeException{
    public KlubNotFoundException(String message){
        super(message);
    }

}
