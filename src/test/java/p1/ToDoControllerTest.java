package p1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.junit.Before;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
/*
Unit Testing for ToDoController's requestmapping methods: GET (getall), ADD, UPDATE
	//TODO: GET one item
*/
public class ToDoControllerTest {

    private String baseUrl = "http://localhost:8080";

    private String endpointToThrowException = "/todo/getall";

    @Autowired
    private TestRestTemplate testRestTemplate;
	private RestTemplate patchRestTemplate;
	
	@Before
	/*
	NOTE: because there is no PATCH support for the implementation that I used of the responseEntity, had to create a workaround.
			It may be possible and better to create all methods using the exchange method as seen in testPatch()
	*/
    public void setup() {

        this.patchRestTemplate = testRestTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
	
    @Test
	/*
	Complete method for Get All
	*/
    public void testGetAll() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/todo/getall", String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	/*
		//TODO: Finish user story; test for incorrect adding for the 400 status...
		NOTE: Also bad hardcoding, need to fix method.
	*/
    public void testPost() {
		ToDo todoItem = new ToDo(1,
                            "ddda", "ddds", "dxc");
		ResponseEntity<String> response = testRestTemplate.postForEntity("/todo/add?title=Cald&description=ddd&dueDateTime=dkkd", todoItem, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	}
	
	@Test
	/*
		//TODO: Finish user story; test happy path when item is found
		NOTE: Also bad hardcoding, need to fix method.
	*/
	public void testPatch() {
		HttpEntity<ToDo> request = new HttpEntity<>(new ToDo(1,
                            "ddda", "ddds", "dxc"));
		ResponseEntity<String> response =
        patchRestTemplate.exchange("/todo/update?id=1&title=Cald&description=ddd&dueDateTime=dkkd", HttpMethod.PATCH, request, String.class);
		assertThat(response, notNullValue());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
	}
	

}






