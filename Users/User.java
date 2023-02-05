package Users;

public class User {
    private String name;
    private Role role;
    private int balance;
    private int films;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getFilms() {
        return films;
    }
    public void setFilms(int films) {
        this.films = films;
    }


    @Override
    public String toString() {
        
        return this.name+","+this.role+","+this.balance+","+this.films;
    }

    
}
