package JavaExam;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore {
    public static void main(String[] args) {
        // Шаг 1: Создать объекты игрушек и добавить их в PriorityQueue
        PriorityQueue<Toy> toyQueue = new PriorityQueue<Toy>((toy1, toy2) -> toy2.getFrequency() - toy1.getFrequency());

        // Примеры игрушек. Вы можете добавить свои данные. данные в строке разделяются пробелом.
        // Частота выпадения указывается в целых числах и их сумма должна быть равной 10
        put("1 Мяч 1", toyQueue );
        put("2 Кукла 5",toyQueue );
        put("3 Пазл 3",toyQueue );
        put("4 Котик 1",toyQueue );


        // Шаг 2: Вызвать метод Get 10 раз и записать результаты в файл

        Integer[] resultArr = new Integer[10];

        for (int i = 0; i < 10; i++) {
            resultArr[i]= get(toyQueue);
        }

        try {
            FileWriter writer = new FileWriter("toy_results.txt");
            for (int i = 0; i < resultArr.length; i++) {
                writer.write( (resultArr[i]==-1? "проверьте провильность ввода данных" :"id игрушки: " + resultArr[i])  +"\n");

            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();

        }
    }
    public static  void put (String str, PriorityQueue<Toy> toyQueue){

        String[] arrStr = str.split(" ");
        if (arrStr.length==3) {
            try {
                int id = Integer.parseInt(arrStr[0]);
                int frequency = Integer.parseInt(arrStr[2]);
                String name = arrStr[1];
                Toy toy = new Toy(id, name, frequency);
                toyQueue.add(toy);
            } catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("Введены некоректные данные. Вводите строку формата \"id(целое число) Названиие частота выпадения(1к10)\" ");
            }
        }else {
            System.out.println("Введены некоректные данные. Вводите строку формата \"id(целое число) Названиие частота выпадения(1к10)\"");
        }
    }
    public static int get(PriorityQueue<Toy> toyQueue){
        Random random = new Random();
        if(!toyQueue.isEmpty()) {
            Toy toy = toyQueue.poll();

            if (toy.count > 0) {
                int ran = random.nextInt(10);
                toy.frequency -= ran;
                toy.count -= 1;
                if (toy.count >= 1) {
                    toyQueue.add(toy);
                }
            }


            return toy.id;
        }
        return -1;
    }
}