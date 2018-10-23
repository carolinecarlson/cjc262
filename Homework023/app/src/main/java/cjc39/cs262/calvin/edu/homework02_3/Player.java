package cjc39.cs262.calvin.edu.homework02_3;

public class Player {
    public int id;
    public String email;
    public String name;

    public Player() {
        this.id = -1;
        this.email = "";
        this.name = "";
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
