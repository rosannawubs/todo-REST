package p1;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="bad request")  // 400
/* Routes the error from the responsemapping methods
	// TODO: finish this class to ensure proper error handling of a bad request (400)
*/
public class ToDoBadReqException extends RuntimeException {
     
 }
