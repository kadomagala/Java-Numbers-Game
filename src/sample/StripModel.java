package sample;

public class StripModel {
    private final int width, height;
    private int numbersAmount;
    private int [][] strip;

    public StripModel(int width, int height, int numbersAmount) {
        this.width = width;
        this.height = height;
        this.numbersAmount = numbersAmount;
        strip = new int[height][width];
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumbersAmount() {
        return numbersAmount;
    }

    public int[][] getStrip() {
        return strip;
    }
}
