package p1;

/*
	ToDo is an item for a to do list.
*/
public class ToDo {

    private final long id;
    private final String title;
    private final String description;
	private final String dueDateTime;

	/*
		Constructor method
			//TODO: possibly need to create more constructors for this to be flexible with implementation
	*/
    public ToDo(long id, String title, String description, String dueDateTime) {
		this.id = id;
        this.title = title;
        this.description = description;
		this.dueDateTime = dueDateTime;
    }


	/*
		Gets ID
	*/
	public long getId() {
        return id;
    }

	/*
		Gets title
	*/
    public String getTitle() {
        return title;
    }

	/*
		Gets Description
	*/
    public String getDescription() {
        return description;
    }
	
	/*
		Gets Due Date and Time
			//TODO: need to implement this is Date Time datatypes
	*/
	public String getDueDate() {
        return dueDateTime;
    }
	
	/*
		Returns the item data in string format
	*/
	public String toString() {
		return "id " + id + "title " + title + "description " + description + "due date and time " + dueDateTime;
	}
}