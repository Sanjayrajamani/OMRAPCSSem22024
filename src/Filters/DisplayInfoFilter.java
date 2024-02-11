package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class DisplayInfoFilter implements PixelFilter {
    public DisplayInfoFilter() {
        System.out.println("Filter running...");
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        System.out.println("Image is " + grid.length + " by "+ grid[0].length);



        int blackCount = 0;
        int whiteCount = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] < 10) blackCount++;
                if (grid[r][c] > 240) whiteCount++;
               // crop(grid,)
            }
        }

        System.out.println(blackCount + " nearly black pixels and " + whiteCount + " nearly white pixels");
        System.out.println("----------------------------------------");
        System.out.println("If you want, you could output information to a file instead of printing it.");

        return img;

    }private short[][] crop(short[][] grid, int r1, int c1, int r2, int c2) {
        short[][] newGrid = new short[r2][c2];
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                newGrid[r][c] = grid[r][c];
            }
        }

        return newGrid;
    }

    public String getAnswer(short[][] grid, int row) {
        int[] arr = new int[5];
        int currLetter = 0;
        for (int col = 106; col <=215; col += 27) {
            currLetter = (col-106)/27;
            for (int p = 0; p < 27; p++) {
                if (grid[row][col+p] > 200) {
                    arr[currLetter]++;
                }

            }

        }

        int maxBlack = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>maxBlack) {
                maxBlack = arr[i];
            }
        }

        if (maxBlack == arr[0]) {
            return "A";
        } else if(maxBlack == arr[1]) {
            return "B";
        } else if(maxBlack == arr[2]) {
            return "C";
        } else if(maxBlack == arr[3]) {
            return "D";
        } else if(maxBlack == arr[4]) {
            return "E";
        } else {
            return null;
        }

    }


}

