import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву файлу в який треба записати: ");
        String fileName = scanner.nextLine();
        File file1 = new File(fileName);
        int mode = 0;

        if (!file1.exists()) {
            System.out.println("Файл не існує. Бажаєте створити файл? (так/ні)");
            String answer = scanner.nextLine();

            if (answer.equals("так")) {
                file1.createNewFile();
                mode = 1;
            } else {
                System.out.println("Програма завершена.");
                return;
            }
        }
        if (mode != 1) {
            System.out.println("Оберіть режим запису:\n1 - перезаписати весь вміст файлу\n2 - дописати новий вміст в кінець файлу");
            mode = scanner.nextInt();
        }

        if (mode == 2) {

            System.out.println("Записуйте: ");
            try (PrintWriter writer = new PrintWriter(new FileWriter(file1, true))) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {

            System.out.println("Записуйте: \n");
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

    }

}

