package sg.edu.nus.iss.day13workshop.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day13workshop.models.Contact;

@Service
public class DatabaseService {

    private File dataDir = new File("some directory");

    public File getDataDir() {
        return dataDir;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    //method to check if there 
    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }

    //method to save the data (from the...) in C:/data
	public boolean save(final Contact contact) {
        File f = new File(this.dataDir, contact.getID());
        try (OutputStream out = new FileOutputStream(f)) { 
            PrintWriter pw = new PrintWriter(out);
            pw.println(contact.getID());
            pw.println(contact.getName());
            pw.println(contact.getEmail());
            pw.println(contact.getphone());
            pw.flush();

            return true;
        } 
        catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            // ex.printStackTrace();
        }
		//change later
		return false;
	}

	//method to read the file, the output will be the contact model
	public Contact read(String fileId) {

        try {

            File f = new File(this.dataDir, fileId);
            Scanner myReader = new Scanner(f);
            while(myReader.hasNextLine()) {
                System.out.println(myReader.nextLine());
            }
            myReader.close();

            Contact contact = new Contact();
            return contact;
            
        } 
        catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            // ex.printStackTrace();
            return null;
        }

        

    }
    
}
