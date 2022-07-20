package sg.edu.nus.iss.day13workshop;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.day13workshop.services.DatabaseService;

@SpringBootApplication
public class Day13workshopApplication implements ApplicationRunner{

	@Autowired
	DatabaseService dbSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day13workshopApplication.class, args);
	}

	@Override 
	public void run(ApplicationArguments args) {

		//run mvnw spring-boot:run -Dspring-boot.run.arguments="--dataDir=c:\data" in the command line
		// as there is an argument we need to include the -Dspring..... when running the code in the command line

		//if any of the args contain dataDir we get the value of the argument and store it in a String variable
		// and set the location of the directory with the dataDir variable AKA C:/data
		if (args.containsOption("dataDir")){
			final String dataDir = args.getOptionValues("dataDir").get(0);
			dbSvc.setDataDir(new File(dataDir));

			if(!dbSvc.isDataDirValid()) {
				System.err.printf("%s does not exist, is not a directory or not writable", dataDir);
				//if there is error exit the programme (referring to the line of code below lol)
				System.exit(-1);
			}

			System.out.printf("Using %s as data directory\n", dataDir);
		}
		else{
			dbSvc.setDataDir(new File("c:/data"));
		}
	}

	//method to save the data (from the...) in C:/data
	public boolean save() {
		//change later
		return false;
	}

	//method to read the file
	public void read(String fileId) {}

}
