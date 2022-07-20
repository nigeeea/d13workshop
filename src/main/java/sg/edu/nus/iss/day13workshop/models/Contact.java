package sg.edu.nus.iss.day13workshop.models;

//UUID is a random ID generator
import java.util.UUID;

public class Contact {
    private final String id;
    private String name;
    private String email;
    private String phone;

    public Contact() {
        this.id = UUID.randomUUID().toString().substring(0,8);
    }

    public Contact(String id) {
        this.id = id;
    }


    public String getID () { return this.id; }

    //creating the getter

    public String getName() { return this.name; }
    public void setName() {this.name=name; }

    public String getEmail() { return this.email; }
    public void setEmail() { this.email=email; }

    public String getphone() { return this.phone; }
    public void setPhone() { this.phone=phone; }

    @Override
    public String toString() {
        return "Contact [email=" + email + ",id=" + id + ", name=" + name + ",phone=" + phone + "]"; 
    }


    
}
