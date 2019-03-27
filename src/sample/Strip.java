package sample;

import java.util.HashMap;
import java.util.Random;

public class Strip {
    static public int[][] strip;
    public static NumberBean[] beans;
    public static int rowSize;
    public static int colSize;
    public static int numOfBeans;

    public static void Init(int numOfBeans, int rowSize, int colSize) {
        beans = new NumberBean[numOfBeans];
        Strip.rowSize = rowSize;
        Strip.colSize = colSize;
        Strip.numOfBeans = numOfBeans;
        strip = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++)
            for (int j = 0; j < colSize; j++)
                strip[i][j] = -1;
        Random r = new Random();
        Cordinates cordinates = new Cordinates(r.nextInt(rowSize), r.nextInt(colSize));
        strip[cordinates.getX()][cordinates.getY()] = 0;
        NumberBean zeroBean = new NumberBean();
        zeroBean.setValue(0);
        zeroBean.setCords(cordinates);
        zeroBean.isPrime = false;
        beans[0] = zeroBean;
        for (int i = 1; i < numOfBeans; i++) {
            int value = r.nextInt(10 * numOfBeans) + 1;

            Cordinates cords;
            do {
                cords = new Cordinates(r.nextInt(rowSize), r.nextInt(colSize));
            } while (strip[cords.getX()] [cords.getY()]!=-1);
            NumberBean bean = new NumberBean();
            bean.setCords(cords);
            bean.setValue(value);
            bean.isPrime();
            beans[i] = bean;
            strip[cords.getX()][cords.getY()] = value;
        }
    }

}
