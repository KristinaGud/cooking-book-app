package en.kristina.cookingbookapp.communicator.service;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunicatorServiceTest {

	@Autowired
	private CommunicatorService communicatorService;

	@Test
	public void localIdGenerated() throws NoSuchAlgorithmException {
		String title = "Backed potatoes by Alice";
		String ingredients = " fresh potatoes, salt, rosemary, olive oil";
		String generatedId = communicatorService.generateUniqueId(title, ingredients);

		Assert.assertEquals(generatedId, ("0B8ED288D44A3CBC0044619283AA6F4F"));

	}


}
