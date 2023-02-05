package Users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserBase {
    ArrayList<User> recordUsers = new ArrayList<>();

    public ArrayList<User> getRecordusers() {
        return recordUsers;
    }

    public void setRecordusers(ArrayList<User> recordUsers) {
        this.recordUsers = recordUsers;
    }

    public User creatUser(){
        Scanner in=new Scanner(System.in);
        User user=new User();
        System.out.println("Введите имя: ");
        user.setName(in.nextLine());
        System.out.println("Введите баланс");
        user.setBalance(in.nextInt());
        System.out.println("Выберите роль:\nАдминистратор - 1\nПользователь - другое");
        if ((in.nextInt()) == 1) {
            user.setRole(Role.admin);
        } else {
            user.setRole(Role.user);
        }
        return user;
    }

    public void fillRecord(ArrayList<User> record) {
        boolean flag = true;
        while (flag) {
            System.out.println("Вы хотите добавить нового человека? 1 - Да, 2 - Нет");
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            if (a == 1) {
                record.add(creatUser());
            } else if (a == 2) {
                System.out.println("База людей сохранена");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setRecordusers(record);
    }

    public void outputToTxtFile(String filename, ArrayList<User> record) throws IOException {
        filename = filename + ".txt";
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (User x : record) {
                fileWriter.write(x.toString());
                fileWriter.write("\n");
            }
            fileWriter.flush();
        }
    }

    public ArrayList<User> inputFile(String filename) throws IOException {
        String[] parsing;
        ArrayList<User> output = new ArrayList<>();
        java.io.File file = new File(filename + ".txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            parsing = in.nextLine().split(",");
            User user = new User();
            user.setName(parsing[0]);
            user.setRole(Role.valueOf(parsing[1]));
            user.setBalance(Integer.parseInt(parsing[2]));
            user.setFilms(Integer.parseInt(parsing[3]));
            output.add(user);
        }
        return output;
    }

    public User Worker(ArrayList<User> pb) {
        String a;
        System.out.println("Введите Ваше имя");
        Scanner in = new Scanner(System.in);
        a = in.nextLine();
        for (User person : pb) {
            if (person.getName().contains(a)) {
                return person;
            }
        }
        return null;
    }
}
