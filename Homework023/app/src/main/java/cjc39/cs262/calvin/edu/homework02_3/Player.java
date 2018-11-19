package cjc39.cs262.calvin.edu.homework02_3;

/**
 * private-package class Player
 * creates an object-oriented class Player for the Monopoly game
 * @author  Caroline Carlson
 * @version 1.0
 * @since   2018-18-11
 */
class Player {
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
