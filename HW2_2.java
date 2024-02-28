import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;





class BubbleSort {
    public static void sort(int[] mas) {
        Logger logger = Logger.getLogger(BubbleSort.class.getName());
        try {
            // Создаем обработчик для записи в файл
            java.util.logging.FileHandler fh = new java.util.logging.FileHandler("log.txt");
            // Создаем кастомный форматтер с нужным форматом даты и времени
            fh.setFormatter(new java.util.logging.Formatter() {
                private final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

                @Override
                public String format(java.util.logging.LogRecord record) {
                    return dateFormat.format(new java.util.Date(record.getMillis())) + " " + record.getMessage() + System.lineSeparator();
                }
            });

            // // Создаем обработчик для вывода в консоль
            // ConsoleHandler ch = new ConsoleHandler();
            // ch.setFormatter(new java.util.logging.Formatter() {
            //     private final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

            //     @Override
            //     public String format(java.util.logging.LogRecord record) {
            //         return dateFormat.format(new java.util.Date(record.getMillis())) + " " + record.getMessage() + System.lineSeparator();
            //     }
            // });
            logger.setUseParentHandlers(false);
            // logger.addHandler(ch);
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // logger.setLevel(Level.ALL);
        // for (Handler handler : logger.getHandlers()) {
        //     if (handler instanceof java.util.logging.FileHandler) {
        //         handler.setLevel(Level.INFO);
        //     }
        // }
        int n = mas.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (mas[j] > mas[j + 1]) {
                    int temp = mas[j];
                    mas[j] = mas[j + 1];
                    mas[j + 1] = temp;
                    swapped = true;
                }
            }
            logger.log(Level.INFO, Arrays.toString(mas));
            if (!swapped) {
                break;

            }
        }
    }

}


// Не удаляйте этот класс - он нужен для вывода результатов на экран и проверки
public class HW2_2 {
    public static void main(String[] args) {
        int[] arr = {};
        if (args.length == 0) {
            // При отправке кода на Выполнение, вы можете варьировать эти параметры
            arr = new int[]{9, 4, 8, 3, 1};
        } else {
            arr = Arrays.stream(args[0].split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        BubbleSort ans = new BubbleSort();
        ans.sort(arr);
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}