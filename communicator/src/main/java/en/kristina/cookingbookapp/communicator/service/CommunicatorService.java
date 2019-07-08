package en.kristina.cookingbookapp.communicator.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CommunicatorService {
	protected String generateUniqueId(String tittle, String ingredients) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update((tittle+ingredients).getBytes());
		byte[] digest = md.digest();
		return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}
}
