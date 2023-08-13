package com.company.FileWriters;

import com.company.Sort.MergeSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FilesOpener {
    public static void OpenFileAndWriteSortedToOutFile(String[] args) {
        String typeOfSort = "up";
        String typeOfInputValues = "integer";
        boolean isTypeOfSortExplicitlySpecified = false;

        if (args.length == 0) {
            System.out.println("Вы не ввели аргументы командной строки. Программа не может продолжить работу.");
            System.exit(0);
        } else if (args[0].contains("-d") && args[1].contains("-i")) {
            typeOfSort = "down";
            isTypeOfSortExplicitlySpecified = true;
        } else if (args[0].contains("-d") && args[1].contains("-s")) {
            typeOfSort = "down";
            typeOfInputValues = "string";
            isTypeOfSortExplicitlySpecified = true;
        } else if (args[0].contains("-a") && (args[1].contains("-i"))) {
            typeOfSort = "up";
            isTypeOfSortExplicitlySpecified = true;
        } else if (!args[0].contains("-d") && args[0].contains("-i")) {
            typeOfSort = "up";
        } else if (!args[0].contains("-d") && args[0].contains("-s")) {
            typeOfSort = "up";
            typeOfInputValues = "string";
        } else {
            System.out.println("Неверно введен тип данных или тип значений в файлах. Порядок записи: ");
            System.out.println("Первый аргумент - порядок сортировки (необязательный) -a по возрастанию или -d по убыванию, по умолчанию сортировка по возрастанию.");
            System.out.println("Второй аргумент - тип сортируемых данных в файлах (-i для целочисленных значений или -s для строковых значений.");
            System.out.println("Третий аргумент - название выходного файла, пример: out.txt");
            System.out.println("Далее следуют названия файлов, которые вы хотите отсортировать.");
            System.out.println("Общий пример записи: -d -s out.txt in1.txt in2.txt in3.txt");
            System.out.println("Пожалуйста, перезапустите программу, введя правильные аргументы командной строки.");
            System.exit(0);
        }

        ArrayList<Integer> integerValuesOfInputFiles = getIntegerValuesOfInputFiles(args, isTypeOfSortExplicitlySpecified);
        ArrayList<String> stringValuesOfInputFiles = getStringValuesOfInputFiles(args, isTypeOfSortExplicitlySpecified);

        int[] arrayOfFileInts = new int[integerValuesOfInputFiles.size()];
        String[] arrayOfFileStrings = new String[stringValuesOfInputFiles.size()];

        for (int i = 0; i < arrayOfFileInts.length; i++) {
            arrayOfFileInts[i] = integerValuesOfInputFiles.get(i);
        }
        for (int i = 0; i < arrayOfFileStrings.length; i++) {
            arrayOfFileStrings[i] = stringValuesOfInputFiles.get(i);
        }

        if (typeOfSort.equals("down") && typeOfInputValues.equals("integer") && !(arrayOfFileInts.length == 0)) {
            MergeSort.mergeIntSortDown(arrayOfFileInts);
            WriteIntOutputFile(args, arrayOfFileInts, isTypeOfSortExplicitlySpecified);
        } else if (typeOfSort.equals("up") && typeOfInputValues.equals("integer") && !(arrayOfFileInts.length == 0)) {
            MergeSort.mergeIntSortUp(arrayOfFileInts);
            WriteIntOutputFile(args, arrayOfFileInts, isTypeOfSortExplicitlySpecified);
        } else if (typeOfSort.equals("down") && typeOfInputValues.equals("string") && arrayOfFileInts.length == 0) {
            MergeSort.mergeStringSortDown(arrayOfFileStrings);
            WriteStringOutputFile(args, arrayOfFileStrings, isTypeOfSortExplicitlySpecified);
        } else if (typeOfSort.equals("up") && typeOfInputValues.equals("string") && arrayOfFileInts.length == 0) {
            MergeSort.mergeStringSortUp(arrayOfFileStrings);
            WriteStringOutputFile(args, arrayOfFileStrings, isTypeOfSortExplicitlySpecified);
        } else {
            System.out.println("Вы ввели неверный тип данных.");
            System.out.println("Первый аргумент - порядок сортировки (необязательный) -a по возрастанию или -d по убыванию, по умолчанию сортировка по возрастанию.");
            System.out.println("Второй аргумент - тип сортируемых данных в файлах (-i для целочисленных значений или -s для строковых значений.");
            System.out.println("Третий аргумент - название выходного файла, пример: out.txt");
            System.out.println("Далее следуют названия файлов, которые вы хотите отсортировать.");
            System.out.println("Общий пример записи: -d -s out.txt in1.txt in2.txt in3.txt");
            System.out.println("Пожалуйста, перезапустите программу, введя правильные аргументы командной строки с правильным указанием типа данных.");
            System.exit(0);
        }
    }

    public static ArrayList<Integer> getIntegerValuesOfInputFiles(String[] args, boolean isTypeOfSortExplicitlySpecified) {
        try {
            String projectPath = System.getProperty("user.dir");
            File separator = new File(File.separator);
            ArrayList<Integer> arrayList = new ArrayList<>();

            int firstArgument = 0;

            if (isTypeOfSortExplicitlySpecified) {
                firstArgument = 3;
            } else {
                firstArgument = 2;
            }

            for (int i = firstArgument; i < args.length; i++) {
                File file_in = new File(projectPath + separator + args[i]);

                Scanner scanner = new java.util.Scanner(file_in);
                while (scanner.hasNextInt()) {
                    arrayList.add(scanner.nextInt());
                }
            }
            return arrayList;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл с указанным вами названием не найден в корне папки проекта.");
            System.out.println("Пожалуйста, проверьте введённое имя файла. Возможно, вы забыли указать формат .txt у введённого имени.");
            System.exit(0);
        }
        return new ArrayList<>();
    }

    public static ArrayList<String> getStringValuesOfInputFiles(String[] args, boolean isTypeOfSortExplicitlySpecified) {
        try {
            String projectPath = System.getProperty("user.dir");
            File separator = new File(File.separator);
            ArrayList<String> arrayList = new ArrayList<>();

            int firstArgument = 0;

            if (isTypeOfSortExplicitlySpecified) {
                firstArgument = 3;
            } else {
                firstArgument = 2;
            }

            for (int i = firstArgument; i < args.length; i++) {
                File file = new File(projectPath + separator + args[i]);
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    if (!line.contains(" ")) {
                        arrayList.add(line);
                    }
                    line = reader.readLine();
                }
            }
            return arrayList;
        } catch (FileNotFoundException e) {
            System.out.println("Файл с указанным вами названием не найден в корне папки проекта");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return new ArrayList<>();
    }

    public static void WriteIntOutputFile(String[] args, int[] arrayOfFileInts, boolean isTypeOfSortExplicitlySpecified) {
        try {
            int positionOfOutputFile = 0;
            int acceptableArgsLength = 0;
            if (isTypeOfSortExplicitlySpecified) {
                positionOfOutputFile = 2;
                acceptableArgsLength = 4;
            } else {
                positionOfOutputFile = 1;
                acceptableArgsLength = 3;
            }
            if (args.length > acceptableArgsLength) {
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter(args[positionOfOutputFile]));
                for (int i = 0; i < arrayOfFileInts.length; i++) {
                    outputWriter.write(Integer.toString(arrayOfFileInts[i]));
                    outputWriter.newLine();
                }
                outputWriter.flush();
                outputWriter.close();
            } else {
                System.out.println("Не введены имена сортируемых файлах. Перезапустите программу с верным написанием командных аргументов.");
                System.exit(0);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static void WriteStringOutputFile(String[] args, String[] arrayOfFileStrings, boolean isTypeOfSortExplicitlySpecified) {
        try {
            int positionOfOutputFile = 0;
            int acceptableArgsLength = 0;
            if (isTypeOfSortExplicitlySpecified) {
                positionOfOutputFile = 2;
                acceptableArgsLength = 4;
            } else {
                positionOfOutputFile = 1;
                acceptableArgsLength = 3;
            }

            if (args.length >= acceptableArgsLength) {
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter(args[positionOfOutputFile]));
                for (int i = 0; i < arrayOfFileStrings.length; i++) {
                    outputWriter.write(arrayOfFileStrings[i]);
                    outputWriter.newLine();
                }
                outputWriter.flush();
                outputWriter.close();
            } else {
                System.out.println("Не введены имена сортируемых файлов. Перезапустите программу с верным написанием командных аргументов.");
                System.exit(0);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
