package Q3;

import java.util.Scanner;

public class Q3
{
    public static void main(String[] args)
    {
        int[][] b = new int[6][6];
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 6; ++i)
        {
            for(int j = 0; j < 6; ++j)
            {
                b[i][j] = scanner.nextInt();
            }
        }
        Board board = new Board(b);
        int n = scanner.nextInt();
        for(int i = 0; i < n; ++i)
        {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            boolean match = board.matchPair(x1, y1, x2, y2);
            // board.print();
            if(match)
            {
                System.out.println("pair matched");
            }
            else
            {
                System.out.println("bad pair");
            }
        }
        scanner.close();
    }
}

class Board
{
    private int[][] board;
    private final int width = 6;
    private final int height = 6;
    public Board(int[][] board)
    {
        this.board = board;
    }
    public boolean matchPair(int y1, int x1, int y2, int x2)
    {
        if(board[x1][y1] != board[x2][y2])
        {
            return false;
        }
        else
        {
            boolean findFlag = find(x2, y2, x1, y1 - 1, 0, -1, 0) || find(x2, y2, x1 + 1, y1, 1, 0, 0) || find(x2, y2, x1, y1 + 1, 0, 1, 0) || find(x2, y2, x1 - 1, y1, -1, 0, 0);
            if(findFlag)
            {
                board[x1][y1] = -1;
                board[x2][y2] = -1;
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public void print()
    {
        for(int i = 0; i < height; ++i)
        {
            for(int j = 0; j < width; ++j)
            {
                System.out.print((board[i][j] >= 0 ? board[i][j] : "X") + " ");
            }
            System.out.println();
        }
    }
    private boolean find(int targetX, int targetY, int x, int y, int moveX, int moveY, int turn)
    {
        // x, y: current x, y, hold by caller
        // movex movey: direction
        if(turn > 2)
        {
            return false;
        }
        if(x < -1 || x > width || y < -1 || y > height)
        {
            return false;
        }
        else if(targetX == x && targetY == y)
        {
            return true;
        }
        else if(x >= 0 && x < width && y >= 0 && y < height && board[x][y] != -1)
        {
            // not empty
            return false;
        }
        
        for(int dir = 0; dir < 4; ++dir)
        {
            int dirX = 1, dirY = 1;
            switch (dir)
            {
                case 0: // up
                    dirX = 0;
                    dirY = -1;
                    break;
                case 1: // right
                    dirX = 1;
                    dirY = 0;
                    break;
                case 2: // down
                    dirX = 0;
                    dirY = 1;
                    break;
                case 3: // left
                    dirX = -1;
                    dirY = 0;
            }
            boolean isTurn = !((moveX == dirX) && (moveY == dirY));
            if(find(targetX, targetY, x + dirX, y + dirY, dirX, dirY, turn + (isTurn ? 1 : 0)))
            {
                return true;
            }
        }
        return false;
    }
}