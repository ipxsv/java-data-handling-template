package com.epam.izh.rd.online.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/main/resources/sensitive_data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String sensitiveData = scanner.nextLine();
         String mask = "$1 **** **** $4";
         Pattern pattern = Pattern.compile("([0-9]{4})\\s([0-9]{4})\\s([0-9]{4})\\s([0-9]{4})");
            Matcher matcher = pattern.matcher(sensitiveData);

        return matcher.replaceAll(mask);
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        Scanner scanner = null;
        String result = "";
        try {
            scanner = new Scanner(new File("src/main/resources/sensitive_data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Pattern pattern = Pattern.compile("\\$\\{[a-z]+_[a-z]+}");
        Matcher matcher = pattern.matcher(scanner.nextLine());
        if (matcher.find()){
            result = matcher.replaceAll(String.valueOf((int)paymentAmount));
        }
        pattern = Pattern.compile("\\$\\{[a-z]+}");
        matcher = pattern.matcher(result);
        if (matcher.find()){
            result = matcher.replaceAll(String.valueOf((int)balance));
        }

        return result;
    }

}
