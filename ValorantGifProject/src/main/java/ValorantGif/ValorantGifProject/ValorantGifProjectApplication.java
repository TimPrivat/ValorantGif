package ValorantGif.ValorantGifProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValorantGifProjectApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ValorantGifProjectApplication.class, args);
		System.out.println("Niklas hat große Hände ;)");
		
		System.out.println(new ApiController().getAgent(new ApiController().getMatchID()));
		
	}

}
