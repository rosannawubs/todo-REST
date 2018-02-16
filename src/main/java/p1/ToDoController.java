package p1;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
/*
	Conroller for ToDo objects, it maps each of the contained methods as seen below to a service, to allow calls from an external source
*/
public class ToDoController {
    private final AtomicLong counter = new AtomicLong();
    List<ToDo> todoList = new ArrayList<ToDo>();

    @RequestMapping(value="/todo/add", method=RequestMethod.POST, produces="application/json")
	/*
		Adding/Posting a ToDo item
	*/
    public ResponseEntity<ToDo> todoa(@RequestParam(value="title") String title,
					@RequestParam(value="description") String description,
					@RequestParam(value="dueDateTime") String dueDateTime) {
		try {
			ToDo todoItem = new ToDo(counter.incrementAndGet(),
                            String.format(title), String.format(description), String.format(dueDateTime));
			todoList.add(todoItem);
			return new ResponseEntity<ToDo>(todoItem, HttpStatus.CREATED);
		}
		catch(IndexOutOfBoundsException e) {
			throw new ToDoBadReqException();
		}
    }
	
	@RequestMapping(value="/todo/update", method=RequestMethod.PATCH)
	/*
		Updating/Patching a ToDo item
	*/
    public ToDo todop(@RequestParam(value="id") int id,
					@RequestParam(value="title", defaultValue="newToDo") String title,
					@RequestParam(value="description", defaultValue="description") String description,
					@RequestParam(value="dueDateTime", defaultValue="02/14/2018") String dueDateTime) {
		ToDo todoItem = new ToDo(id, String.format(title), String.format(description), String.format(dueDateTime));
		try {
			todoList.set((id-1), todoItem);	
			return todoItem;
		}
		catch(IndexOutOfBoundsException e) {
			throw new ToDoException();
		}
		
    }
	
	@RequestMapping(value="/todo/get", method=RequestMethod.GET)
	/*
		Getting one ToDo item
	*/
    public ToDo todog(@RequestParam(value="id") int id) {
		try {
			ToDo todoItem = todoList.get((id-1));	
			return todoItem;
		}
		catch(IndexOutOfBoundsException e) {
			throw new ToDoException();
		}
		
    }
	
	@RequestMapping(value="/todo/getall", method=RequestMethod.GET, produces="application/json")
	/*
		Getting all ToDo items from list
	*/
    public List<ToDo> todoga() {
						
		return todoList;
	}


}