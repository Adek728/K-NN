import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double k;


        List<String> listTrain = read("D:\\STUDIA\\4 semestre\\NAI\\NAI\\src\\iris.data.csv");
        List<String> listTest = read("D:\\STUDIA\\4 semestre\\NAI\\NAI\\src\\iris.test.data.csv");

        List<Double> listNumberEnd = new ArrayList<>();
        List<String> listNameEnd = new ArrayList<>();
        HashMap<String, Integer> dokladnailosc = new HashMap<>();

        int x = 1;
        do{
                System.out.println("Podaj k: ");

                k = scanner.nextInt();

                if(k!=0){
                    double dokladnosc = 0;
                    for(String s : listTest) {
                        String[] testLine = s.split(",");
                        for (String ss : listTrain) {
                            String[] trainLine = ss.split(",");
                            double Value = 0;
                            for (int i = 0; i < trainLine.length - 2; i++) {
                                double singleValue = Math.pow(Double.parseDouble(trainLine[i]) - Double.parseDouble(testLine[i]), 2);
                                Value += Math.sqrt(singleValue);
                            }
                            listNumberEnd.add(Value);
                            listNameEnd.add(trainLine[trainLine.length - 1]);
                        }

                        for (int i = 0; i < k; i++) {
                            int small = 0;
                            for (int j = 0; j < listNumberEnd.size(); j++) {
                                if (listNumberEnd.get(small) > listNumberEnd.get(j)) {
                                    small = j;
                                }
                            }

                            if (dokladnailosc.get(listNameEnd.get(small)) == null){
                                dokladnailosc.put(listNameEnd.get(small),1);
                            }else{
                                dokladnailosc.replace(listNameEnd.get(small), dokladnailosc.get(listNameEnd.get(small))+1);
                            }

                            listNumberEnd.remove(small);
                            listNameEnd.remove(small);
                        }


                        String name="";
                        for (String key : dokladnailosc.keySet()) {
                            if(name.equals("")){
                                name = key;
                            }else if(dokladnailosc.get(name) < dokladnailosc.get(key)){
                                name = key;
                            }
                        }

                        if(name.equals(testLine[testLine.length - 1])){
                            dokladnosc++;
                        }

                        dokladnailosc.clear();
                        listNameEnd.clear();
                        listNumberEnd.clear();
                    }

                    System.out.println("Dokladnosc dla " + k + " to: " +(dokladnosc * 100) / listTest.size() + "%");
                    System.out.println("==================================");
                    System.out.println();
                }else{
                    System.out.println("Podales 0, program sie zamyka");
                    x=0;
                }


        }while(x!=0);

    }

    public static List<String> read(String file){
        List<String> list = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(file));
            while(scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
