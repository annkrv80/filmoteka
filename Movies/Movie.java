package Movies;

public class Movie {
    private String name;
    private String description;
    private int cost;
    
    
    public Movie(String name, String description, int cost){
        this.name=name;
        this.description=description;
        this.cost=cost;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    @Override
    public String toString() {
        
        return this.name +","+ this.description + "," + this.cost;
    }
}
