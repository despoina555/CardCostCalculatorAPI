package despina.cardcost.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class  ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

}
