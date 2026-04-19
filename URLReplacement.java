package org.example;

import java.io.*; 

public class URLReplacement {
    public static void main(String[] args) {
        
        String inputFile = "c:/URLReplacement/inputFile.txt";   // Путь к исходному файлу
        String outputFile = "c:/URLReplacement/outputFile.txt";  // Путь к файлу с результатом обработки

        // try-with-resources: автоматически закрываем потоки после выполнения программы
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line; 
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    char lastChar = line.charAt(line.length() - 1); 
                    if (lastChar == ';' || lastChar == '.') {
                        line = line.substring(0, line.length() - 1);
                    }
                }
                if (line.startsWith("hxxp[:]//")) {
                    line = line.substring(9); 
                }
                else if (line.startsWith("hxxps[:]//")) {
                    line = line.substring(10); 
                }
                line = line.replace("[.]", ".");
              
                int slashIndex = line.indexOf('/'); 
                if (slashIndex != -1) { 
                    line = line.substring(0, slashIndex); 
                }
             
                writer.write(line);
                writer.newLine(); 
            }

            System.out.println("Обработка файла завершена. Результат сохранён в файл " + outputFile);

        } catch (FileNotFoundException e) {
            // Ошибка: файл 1.txt не найден
            System.err.println("Ошибка: файл " + inputFile + " не найден!");
            System.err.println("Подробности: " + e.getMessage());
        } catch (IOException e) {
            // Ошибка: проблема при чтении или записи файла
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}
