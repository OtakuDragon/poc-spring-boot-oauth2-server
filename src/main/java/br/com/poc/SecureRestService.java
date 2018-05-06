package br.com.poc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureRestService {
	
	@GetMapping("/secureApi")
	public String secureService() {
		return "Very important protected service";
	}
}
