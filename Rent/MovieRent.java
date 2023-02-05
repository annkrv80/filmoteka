package Rent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Movies.Movie;
import Users.User;

public class MovieRent {
    String userName;
    User serviceAdmin;
    String rentedMovie;

    ArrayList <MovieRent> rentMovieArrayList = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public String getRentedMovie() {
        return rentedMovie;
    }

    public void setRentedMovie(String rentedMovie) {
        this.rentedMovie = rentedMovie;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getServiceAdmin() {
        return serviceAdmin;
    }

    public void setServiceAdmin(User serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    public ArrayList<MovieRent> getRentMovieArrayList() {
        return rentMovieArrayList;
    }

    public void setRentMovieArrayList(ArrayList<MovieRent> rentMovieArrayList) {
        this.rentMovieArrayList = rentMovieArrayList;
    }

    public MovieRent createRentMovie(ArrayList <User> pb, ArrayList <Movie> fb, User admin) {
        MovieRent rentMovie = new MovieRent();
        String a;
        String b;
        System.out.println("Введите имя: ");
        Scanner in = new Scanner(System.in);
        a = in.nextLine();
        System.out.println("Введите название фильма: ");
        b = in.nextLine();
       // for (User user : pb) {
         // System.out.println("Введите название фильма: ");
      //  b = in.nextLine();
        for (Movie movie : fb) {
            for(User user: pb){
                if (user.getName().contains(a)) {
                    if(user.getFilms()<=5){
                        rentMovie.setUserName(user.getName());
                    }else{
                        System.out.println("У вас больше 5 фильмов");
                        break;
                    }
                }
                if (movie.getName().contains(b)){
                    int films=user.getFilms()+1;
                    user.setFilms(films);
                    int basance=user.getBalance()-movie.getCost();
                    user.setBalance(basance);
                    rentMovie.setRentedMovie(movie.getName());    
            }
        }
        if ((rentMovie.getUserName() == null) || (rentMovie.getRentedMovie()==null)){
            System.out.println("Запись пустая");
        }else {
            rentMovie.setServiceAdmin(admin);
        }
        
    }
        return rentMovie;
}

    public void fillRecord(ArrayList<MovieRent> record,ArrayList <User> pb, ArrayList <Movie> fb, User admin) {
        boolean flag = true;
                while (flag) {
            System.out.println("Вы хотите добавить новую запись об аренде фильма? 1 - Да, 2 - Нет");
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            if (a == 1) {
                record.add(createRentMovie(pb,fb,admin));
            } else if (a == 2) {
                System.out.println("База записей сохранена");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setRentMovieArrayList(record);
    }
   
  
     
    public void outputToTxtFile(String filename, ArrayList<MovieRent> record) throws IOException {
        filename = filename + ".txt";
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (MovieRent x : record) {
                fileWriter.write(x.toString());
                fileWriter.write("\n");
            }
            fileWriter.flush();
        }
    }

    public ArrayList<MovieRent>  inputFile(String filename, ArrayList <User> pb) throws IOException {
        String[] parsing;
        ArrayList<MovieRent> output = new ArrayList<>();
        java.io.File file = new File(filename +".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");
            MovieRent rentMRent = new MovieRent();
            rentMRent.setUserName(parsing[0]);
            rentMRent.setRentedMovie(parsing[1]);
            for (User x : pb
            ) {
                if (x.getName().contains(parsing[0])) {
                    rentMRent.setServiceAdmin(x);
                }
            }

            output.add(rentMRent);

        }
        return output;
    }

    @Override
    public String toString() {
       return userName + ',' + rentedMovie;
    }
    

}
