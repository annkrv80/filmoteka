package Movies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieBase {
    ArrayList<Movie> recordMovie = new ArrayList<>();
    
    public ArrayList<Movie> getRecordmovie() {
        return recordMovie;
    }

    public void setRecordmovie(ArrayList<Movie> recordMovie) {
        this.recordMovie = recordMovie;
    }
    
    public Movie creatMovie(){
        Scanner in=new Scanner(System.in);
        System.out.println("Введите название фильма: ");
        Movie movie=new Movie(null, null, 0);
        movie.setName(in.nextLine());
        System.out.println("Введите описание фильма: ");
        movie.setDescription(in.nextLine());
        System.out.println("Введите стоимость аренда: ");
        movie.setCost(in.nextInt());
        return movie;
    }

    public void fillRecord(ArrayList<Movie> record) {
        boolean flag = true;
                while (flag) {
            System.out.println("Вы хотите добавить новый фильм? 1 - Да, 2 - Нет");
            Scanner input = new Scanner(System.in);
            int a = input.nextInt();
            if (a == 1) {
                record.add(creatMovie());
            } else if (a == 2) {
                System.out.println("База фильмов сохранена");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setRecordmovie(record);
    }

    public void outputToTxtFile(String filename, ArrayList<Movie> record) throws IOException {
        filename = filename + ".txt";
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (Movie x : record) {
                fileWriter.write(x.toString());
                fileWriter.write("\n");
            }
            fileWriter.flush();
        }
    }

    public ArrayList<Movie> inputFile(String filename) throws IOException {
        String[] parsing;
        ArrayList<Movie> output = new ArrayList<>();
        java.io.File file = new File(filename +".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");
            Movie movie=new Movie(null, null, 0);
            movie.setName(parsing[0]);
            movie.setDescription(parsing[1]);
            movie.setCost(Integer.parseInt(parsing[2]));
            output.add (movie);
        }
        return output;
    }

    public void changeDescript() {
        try {
            Scanner in=new Scanner(System.in);
            System.out.println("Введите название фильма для редактирования: ");
            String nameFilm=in.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader("movieBase.txt"));
            String row;
            while (((row = reader.readLine()) != null)){
                String[] line = row.split(",");
                if (line[0].equals(nameFilm)){                    
                    String fileName = "movieBase.txt";
                    String search = line[1];
                    System.out.println("Введите новое описание: ");
                    String replace = in.nextLine();
                    Charset charset = StandardCharsets.UTF_8;
                    Path path = Paths.get(fileName);
                    Files.write(path,
                        new String(Files.readAllBytes(path), charset).replace(search, replace)
                        .getBytes(charset));                   
                    }                   
            }                                       
        }  catch  (IOException e){
            e.printStackTrace();
        }            
    }

    public void deleteMovie() {    
        System.out.println("Введите фильм для удаления");  
        Scanner in=new Scanner(System.in);
        String nameFilm=in.nextLine();      
        try{
            File file = new File("movieBase.txt");
            List<String> out = Files.lines(file.toPath())
                                .filter(line -> !line.contains(nameFilm))
                                .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE,
             StandardOpenOption.TRUNCATE_EXISTING);
     
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
