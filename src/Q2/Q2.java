package Q2;

import java.util.Scanner;

public class Q2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int board[][] = new int[10][10];
        for(int i = 0; i < 10; ++i)
        {
            for(int j = 0; j < 10; ++j)
            {
                board[i][j] = scanner.nextInt();
            }
        }
        CheeseBoard cheeseBoard = new CheeseBoard(board);
        cheeseBoard.solve();
        cheeseBoard.print();
        scanner.close();
    }
}

class CheeseBoard
{
    private int[][] board;
    public final int width = 10;
    public final int height = 10;
    public CheeseBoard(int[][] board)
    {
        this.board = board;
    }
    public void print()
    {
        for(int i = 0; i < height; ++i)
        {
            for(int j = 0; j < width; ++j)
            {
                int val = board[i][j];
                if(val <= 1)
                {
                    System.out.print(val);
                }
                else
                {
                    System.out.print("X");
                }
                if(j != width - 1)
                {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
    public void solve()
    {
        for(int i = 0; i < height; ++i)
        {
            for(int j = 0; j < width; ++j)
            {
                if(canPlace(i, j))
                {
                    board[i][j] = 2;
                }
            }
        }
    }
    private boolean canPlace(int x, int y)
    {
        for(int iter = 0; iter < 4; ++iter)
        {
            int moveX = 0, moveY = 0;
            switch(iter)
            {
                case 0:
                    moveX = 1;
                    moveY = 0;
                    break;
                case 1:
                    moveX = 1;
                    moveY = 1;
                    break;
                case 2:
                    moveX = 0;
                    moveY = 1;
                    break;
                case 3:
                    moveX = -1;
                    moveY = 1;
                    break;
            }
            int count = 1;
            for(int dir = 0; dir < 2; ++dir)
            {
                int currX = x;
                int currY = y;
                for(int c = 1; c < 5; ++c)
                {
                    currX += moveX;
                    currY += moveY;
                    // System.out.println(currX + " " + currY);
                    if(currX < 0 || currX >= width || currY < 0 || currY >= height)
                    {
                        break;
                    }
                    // System.out.println(board[currX][currY]);
                    if(board[currX][currY] == 1)
                    {
                        count += 1;
                    }
                    else
                    {
                        // System.out.println("bonk");
                        break;
                    }
                }
                moveX = -moveX;
                moveY = -moveY;
            }
            if(count >= 5)
            {
                return true;
            }
        }
        return false;
    }
}