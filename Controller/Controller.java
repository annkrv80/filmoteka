package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Movies.Movie;
import Movies.MovieBase;
import Rent.MovieRent;
import Users.User;
import Users.UserBase;

public class Controller {

        public Controller() {
        }
    
        public void start () throws IOException {
            User admin;
            ArrayList<Movie> fb;
            ArrayList<User> pb;
            ArrayList<MovieRent> rf;
            MovieBase filmbase = new MovieBase();
            UserBase personbase = new UserBase();
            MovieRent rentFilm = new MovieRent();
            fb = filmbase.inputFile("movieBase");
            pb = personbase.inputFile("userBase");
            rf =rentFilm.inputFile("rentBase", pb);
            admin = personbase.Worker(pb);
            if (admin != null) {
    
                int a;
                System.out.println("""
                        Выберите вариант:
                        1 - Дополнить базу фильмов
                        2 - Дополнить базу людей
                        3 - Дополнить базу записей об аренде фильмов
                        4 - Изменить описание фильма
                        5 - Удалить фильм""");
                Scanner in = new Scanner(System.in);
                a = in.nextInt();
                if (a == 1) {
                    filmbase.fillRecord(fb);
                    filmbase.outputToTxtFile("movieBase", filmbase.getRecordmovie());
                }
                if (a == 2) {
                    personbase.fillRecord(pb);
                    personbase.outputToTxtFile("userBase", personbase.getRecordusers());
                }
                if (a == 3) {
                    rentFilm.fillRecord(rf,pb, fb, admin);
                    rentFilm.outputToTxtFile("rentBase", rentFilm.getRentMovieArrayList());
                    
                }
                if(a == 4){
                    filmbase.changeDescript();
                }
                if(a == 5){
                    filmbase.deleteMovie();
                }
    
    
            }
        }
}
