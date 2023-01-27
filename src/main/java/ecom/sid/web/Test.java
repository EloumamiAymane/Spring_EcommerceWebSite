package ecom.sid.web;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class Test {
	


		@GetMapping("/home")
		public String Home() {
			return "you re in home page";
		}
		@GetMapping("/dashboard")
		public String Dadhboard() {
			return "hello dash";
		}
}
