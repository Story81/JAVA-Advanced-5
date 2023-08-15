package org.example;

/**
 * Задание №5
 * @author Соломенникова Н.М.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {
        public static void main(String[] args) {
        // Указываем путь к входному файлу
        String filePath = "C:\\Users\\CASPER\\IdeaProjects\\Collections\\words.txt";

        try {
            // Читаем файл и получаем список всех слов
            List<String> words = readWordsFromFile(filePath);
            System.out.println(words); //выводим список слов из файла (для наглядности)

            // Сортируем список слов в алфавитном порядке
            Collections.sort(words);

            // Считаем количество повторений каждого слова
            Map<String, Integer> wordCount = wordOccurrences(words);

            // Выводим статистику на консоль
            wordStatistics(wordCount);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
        }
    }

    /**
     * @author Соломенникова Н.М.
     * @see #readWordsFromFile(String) - метод для чтения слов из файла
     * @return возвращает список слов, прочитанных из файла
     */

    private static List<String> readWordsFromFile(String filePath) throws FileNotFoundException {
        // Создаем новый пустой список words для хранения слов
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        // Читаем значения из файла до тех пор, пока есть данные
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();
        return words;
    }

    /**
     * @author Соломенникова Н.М.
     * @see #wordOccurrences(List) - принимает на вход список строк words
     * @return  возвращает словарь, где каждому слову из списка соответствует количество его вхождений.
     */

    private static Map<String, Integer> wordOccurrences(List<String> words) {
        // Создаем пустой словарь wordCount, где ключом - это слово, а значением - количество вхождений
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {    //перебираем слова в словаре
            // Проверяем, содержится ли уже данное слово в словаре wordCount
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);  // Если слово уже содержится, то увеличиваем его текущее значение (количество вхождений) на 1
            } else {
                wordCount.put(word, 1);  //Иначе - добавляем слово в словарь со значением 1
            }
        }
        return wordCount;
    }

    /**
     * @author Соломенникова Н.М.
     * @see #wordStatistics(Map) - принимает на вход словарь wordCount
     * @return выводит статистику - каждое слово и количество его вхождений, а также выводит слова с максимальным количеством вхождений
     */

    private static void wordStatistics(Map<String, Integer> wordCount) {
        System.out.println("\nСтатистика:");
        int maxCount = 0;
        String key = null;
        int value = 0;

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            value = entry.getValue();
            if (value > maxCount) maxCount = value;      // Если текущее количество вхождений больше, чем максимальное maxCount- обновляем значения maxCount
            System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");   // Выводим слово и количество его вхождений
                }
        System.out.println("\nСлова с максимальным количеством повторений: ");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value == maxCount)
                       System.out.println(String.format("- '%s' встречается в файле %d раз(а)", key, value));
        }
    }
}

