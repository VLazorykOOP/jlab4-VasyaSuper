import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        File file;
        boolean fileExists;

        String inputFilePath;
        do {
            System.out.print("Введіть назву файлу з якого потрібно зчитати: ");
            inputFilePath = scanner.nextLine();
            file = new File(inputFilePath);
            fileExists = file.exists();

            if (!fileExists) {
                System.out.println("Файл не знайдено. Спробуйте ще раз.");
            }
        } while (!fileExists);

        System.out.println("Файл " + file.getName() + " знайдено.");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try {
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
            if(mode != 1) {
                System.out.println("Оберіть режим запису:\n1 - перезаписати весь вміст файлу\n2 - дописати новий вміст в кінець файлу");
                mode = scanner.nextInt();
            }
            if (mode == 2) {

                List<String> lines = new ArrayList<>(); // список для зберігання непорожніх рядків

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // перевірка, чи рядок є порожнім або складається лише з пробілів
                        if (!line.trim().isEmpty()) {
                            // вилучення з рядка символів, які не є маленькими латинськими літерами
                            String filteredLine = line.replaceAll("[^a-z]", "");
                            char[] charArray = filteredLine.toCharArray();
                            Arrays.sort(charArray);
                            String sortedLine = new String(charArray);
                            lines.add(sortedLine);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Collections.sort(lines); // сортування рядків

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Текст було успішно дописано у файл " + fileName);
            } else {

                List<String> lines = new ArrayList<>(); // список для зберігання непорожніх рядків

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // перевірка, чи рядок є порожнім або складається лише з пробілів
                        if (!line.trim().isEmpty()) {
                            // вилучення з рядка символів, які не є маленькими латинськими літерами
                            String filteredLine = line.replaceAll("[^a-z]", "");
                            char[] charArray = filteredLine.toCharArray();
                            Arrays.sort(charArray);
                            String sortedLine = new String(charArray);
                            lines.add(sortedLine);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Collections.sort(lines); // сортування рядків

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file1))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Текст було успішно записано у файл " + fileName);
            }

        } catch (IOException e) {
            System.out.println("Виникла помилка при роботі з файлом: " + e.getMessage());
        }
    }
}
