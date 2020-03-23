package com.company;

import java.util.*;


public class Main {
    public static final String ANSI_RESET =  " \u001B[0m";
    public static final String ANSI_GREEN =  " \u001B[32m";
    public static final String ANSI_BLUE =  " \u001B[34m";
    public static final String ANSI_PURPLE =  " \u001B[35m";
    public static final String ANSI_RED =  " \u001B[31m";
    public static final String ANSI_YELLOW =  " \u001B[33m";

    public static void main(String[] args) {
        Map<String, String[]> dictionary =  new HashMap<>();
        Map<String, String[]> bigDictionary =  new HashMap<>();

        dictionary.put("красивый", new String[]{"симпотичный", "румяный", "привлекательный", "прекрасный"});
        dictionary.put("человек", new String[]{"существо", "личность", "персона", "индивидум"});
        dictionary.put("дом", new String[]{"жилище", "помещение", "логово", "кров"});
        dictionary.put("большой", new String[]{"огромный", "массивный", "крупный", "громадный"});

        Set<String> keys =  dictionary.keySet();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            String oldKey = iter.next();
            String[] values = dictionary.get(oldKey);
            bigDictionary.put(oldKey, values);
            for (String newKey: values) {
                List<String> listOfValues = new ArrayList<>(values.length);
                listOfValues.addAll(Arrays.asList(values));
                listOfValues.remove(newKey);
                listOfValues.add(oldKey);
                String[] newValues = new String[listOfValues.size()];
                listOfValues.toArray(newValues);
                bigDictionary.put(newKey, newValues);
            }
        }
        for (Map.Entry<String, String[]> item: bigDictionary.entrySet()) {
            System.out.print(ANSI_BLUE + "Key: "+ item.getKey());
            System.out.println(ANSI_PURPLE +" Values: "+ Arrays.toString(item.getValue()));
        }
        while (true) {
            System.out.println(ANSI_RESET + "Введите слово сочетание: (Например: большой красивый дом) ");
            Scanner scanner = new Scanner(System.in);
            String sentences = scanner.nextLine();
            String[] words = sentences.split(" ");
            for (String word : words) {
                String[] sinonyms = bigDictionary.get(word);
                Random r = new Random();
                if (sinonyms != null) {
                    System.out.print(ANSI_GREEN +sinonyms[r.nextInt(sinonyms.length)] + " ");
                } else {
                    System.out.println(ANSI_RED +" Такого слово сочетания нет к сожалению");
                    System.out.print(ANSI_RED +" Попробуйте еще раз");
                }
            }
            System.out.println();
        }
    }
}