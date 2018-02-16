package p1;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")  // 404
/* Routes the error from the responsemapping methods
	// TODO: finish this class to ensure proper error handling of a runtime exception (404)
*/
public class ToDoException extends RuntimeException {
     // ...
 }
