package org.example;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class URLReplacement {
    public static void main(String[] args) {
        String inputFile = "new_url.txt";         //  Путь к файлу с новым списком
        String outputFile = "url_blacklist.txt";  //  Путь к файлу с основной базой

        try {
            // Построчное чтение url адресов из фала new_url.txt
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            // Множество для хранения уже существующих строк в файле url_blacklist.txt
            Set<String> existingUrls = new HashSet<>();

            // Если файл url_blacklist.txt существует, читаем его содержимое
            File blacklistFile = new File(outputFile);
            if (blacklistFile.exists()) {
                List<String> existingLines = Files.readAllLines(Paths.get(outputFile));
                existingUrls.addAll(existingLines);
            }

            // Список для новых строк, которые нужно добавить
            List<String> newLines = new ArrayList<>();

            // Обрабатываем каждую строку
            for (String line : lines) {
                // Убираем пробелы в начале и в конце строки
                String processedLine = line.trim();

                // Если строка не пустая и заканчивается на ';' или '.', удаляем последний символ
                if (!processedLine.isEmpty() &&
                        (processedLine.endsWith(";") || processedLine.endsWith("."))) {
                    processedLine = processedLine.substring(0, processedLine.length() - 1);
                }

                // Если строка начинается на "hxxp[:]//", удаляем "hxxp[:]//"
                if (processedLine.startsWith("hxxp[:]//")) {
                    processedLine = processedLine.substring(9);
                }
                // Если строка начинается на "hxxps[:]//", удаляем "hxxps[:]//"
                else if (processedLine.startsWith("hxxps[:]//")) {
                    processedLine = processedLine.substring(10);
                }

                // Удаляем все в строке после символа "/" включая этот символ
                int slashIndex = processedLine.indexOf("/");
                if (slashIndex != -1) {
                    processedLine = processedLine.substring(0, slashIndex);
                }

                // Заменяем все вхождения "[.]" на "." во всей строке
                processedLine = processedLine.replace("[.]", ".");

                // Убираем все что идет за [:]
                int colonBracketIndex = processedLine.indexOf("[:]");
                if (colonBracketIndex != -1) {
                    processedLine = processedLine.substring(0, colonBracketIndex);
                }

                // Проверяем, что строка не пустая
                if (!processedLine.isEmpty()) {
                    // Добавляем в файл, если такой строки еще нет
                    if (!existingUrls.contains(processedLine)) {
                        newLines.add(processedLine);
                        existingUrls.add(processedLine);
                        System.out.println("Добавлено: " + processedLine);
                    } else {
                        System.out.println("Пропущено (уже существует): " + processedLine);
                    }
                }
            }

            // Записываем обработанные строки в выходной файл
            if (!newLines.isEmpty()) {
                try (FileWriter fw = new FileWriter(outputFile, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    for (String newLine : newLines) {
                        out.println(newLine);
                    }
                }
                System.out.println("\nОбработка завершена. Добавлено " + newLines.size() + " новых записей.");
            } else {
                System.out.println("Нет новых записей для добавления.");
            }

        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
