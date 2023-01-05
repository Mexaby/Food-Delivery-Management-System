package dao;

import java.io.Serializable;

public class Client implements Serializable {
    private int clientID;
    private String username;
    private String password;
    private int nrTimesOrdered;

    public Client(int clientID, String username, String password) {
        this.clientID = clientID;
        this.username = username;
        this.password = password;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNrTimesOrdered() {
        return nrTimesOrdered;
    }

    public void setNrTimesOrdered(int nrTimesOrdered) {
        this.nrTimesOrdered = nrTimesOrdered;
    }

    @Override
    public String toString(){
        return "ID: " + clientID + "\nUsername: " + username + "\nPassword: " + password;
    }
}
