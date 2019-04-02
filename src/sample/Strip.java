package sample;

import java.util.Random;

public class Strip {
    static public volatile int[][] strip;
    public static ZeroBean zeroBean;
    public static NumberBean[] beans;
    public static int rows;
    public static int cols;
    public static int numOfBeans;
    public static int numbersLeft;


    public static void Init(int _numOfBeans, int _rowSize, int _colSize) {
        numOfBeans = _numOfBeans;
        rows = _rowSize;
        cols = _colSize;
        beans = new NumberBean[numOfBeans-1];
        numbersLeft = numOfBeans - 1;
        strip = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                strip[i][j]=-1;
            }
        }
        boolean setuped[][] = new boolean[rows][cols];
        Random r = new Random();

        int x = r.nextInt(rows);
        int y = r.nextInt(cols);
        setuped[x][y] = true;
        zeroBean = new ZeroBean();
        zeroBean.setZeroCords(new Cordinates(x,y));
        Strip.strip[x][y] = 0;
        int i = 0;
        while(i < numOfBeans-1){
            int value = r.nextInt(numOfBeans * 20) + 1;
            do{
                y = r.nextInt(rows);
                x = r.nextInt(cols);
            }while(setuped[x][y]);
            setuped[x][y] = true;
            Strip.strip[x][y] = value;
            NumberBean bean = new NumberBean(new Cordinates(x, y), value);
            beans[i++] = bean;
        }
        for (var bean: beans) {
            for(var bean2: beans){
                bean.addVetoableChangeListener(bean2);
                bean.addVetoableChangeListener(zeroBean);
            }
            zeroBean.addPropertyChangeListener(bean);
        }
    }

}
