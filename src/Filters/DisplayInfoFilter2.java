package Filters;


        import Interfaces.PixelFilter;
import core.DImage;

        import java.util.ArrayList;
        import java.util.Arrays;

public class DisplayInfoFilter2 implements PixelFilter {
    public DisplayInfoFilter2() {
        System.out.println("Filter running...");
    }

    @Override
    public DImage processImage(DImage img) {


        short[][] grid = img.getBWPixelGrid();

        int count =0;

        System.out.println("Image is " + grid.length + " by " + grid[0].length);

        grid = crop(grid, 0, 0, 666, 300);
        monochrome(grid);

        for (int r = 119; r < grid.length; r += 47) {
            count++;
          //  System.out.println(" "+count+") "+getAnswer(grid, r)+ " Row "+ r);

            try {
                System.out.println(getAnswer(grid, r).toString());
            }catch (NullPointerException e){

            }
        }


        img.setPixels(grid);
        return img;
    }

    private short[][] crop(short[][] grid, int r1, int c1, int r2, int c2) {
        short[][] newGrid = new short[r2][c2];
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                newGrid[r][c] = grid[r][c];
            }
        }

        return newGrid;
    }

    public ArrayList<String> getAnswer(short[][] grid, int row) {
        ArrayList<String> answers = new ArrayList<>();

        int[] arr = new int[5];
        int currLetter = 0;
        for (int col = 106; col <= 215; col += 27) {
            currLetter = (col - 106) / 27;
            for (int p = 0; p < 27; p++) {
                if (grid[row][col + p] < 50) {
                    arr[currLetter]++;

                }

            }

        }

        int maxBlack = Arrays.stream(arr).filter(i -> i >= 0).max().orElse(0);
        //  System.out.println("Black: "+ maxBlack+ " "+ i) ;
        // System.out.println("Maxblack"+maxBlack+" "+arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]);
        System.out.println("MaxBlack: " + maxBlack);
        if(maxBlack == 0) {
            return null;
        }
        else if (maxBlack == arr[0]) {
            answers.add("A");
        } else if (maxBlack == arr[1]) {
            answers.add("B");
        } else if (maxBlack == arr[2]) {
            answers.add("C");
        } else if (maxBlack == arr[3]) {
            answers.add("D");
        } else if (maxBlack == arr[4]) {
            answers.add("E");
        } else {
             answers.add("NO ANSWER");
        }
   return answers;
    }


    public void monochrome(short[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] > 187) {
                    grid[row][col] = 255;
                } else {
                    grid[row][col] = 0;
                }
            }
        }
    }
}

