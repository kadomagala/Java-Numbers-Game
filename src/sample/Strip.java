package sample;

import java.util.HashMap;
import java.util.Random;

public class Strip {
    static public HashMap<Integer, Cordinates>  strip;
    public static NumberBean[] beans;
    public static int rowSize;
    public static int colSize;
    public static int numOfBeans;

    public static void Init(int numOfBeans, int rowSize, int colSize){
        beans = new NumberBean[numOfBeans];
        Strip.rowSize = rowSize;
        Strip.colSize = colSize;
        Strip.numOfBeans = numOfBeans;
        strip = new HashMap<>();
        Random r = new Random();
        Cordinates cordinates = new Cordinates(r.nextInt(rowSize), r.nextInt(colSize));
        strip.put(0,cordinates);
        NumberBean zeroBean = new NumberBean();
        zeroBean.setValue(0);
        zeroBean.setCords(cordinates);
        zeroBean.isPrime = false;
        for(int i = 1; i < numOfBeans; i++){

            int value;
            do {
                value = r.nextInt(10 * numOfBeans) + 1;
            }while (strip.containsKey(value));


            Cordinates cords = new Cordinates(r.nextInt(rowSize), r.nextInt(colSize));
            strip.put(0,cords);
            do{
                cords = new Cordinates(r.nextInt(rowSize), r.nextInt(colSize));
            }while(strip.containsValue(cords));
            NumberBean bean = new NumberBean();
            bean.setCords(cords);
            bean.setValue(value);
            bean.isPrime();
            beans[i] = bean;
            strip.put(value, cords);
        }
    }

}
