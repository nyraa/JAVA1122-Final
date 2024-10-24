package Q1;

import java.util.Scanner;

public class Q1
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < n; ++i)
        {
            String line = scanner.nextLine();
            String[] leftAndRight = line.split("=");
            Equation left_x = new Equation(leftAndRight[0]);
            Equation right_c = new Equation(leftAndRight[1]);
            left_x.parse();
            right_c.parse();

            left_x.setXCount(left_x.getXCount() - right_c.getXCount());
            right_c.setXCount(0);

            right_c.setConstCount(right_c.getConstCount() - left_x.getConstCount());
            left_x.setConstCount(0);
            // case inf sol
            if(left_x.getXCount() == 0 && right_c.getConstCount() == 0)
            {
                System.out.println("Infinite solutions");
            }
            else if(left_x.getXCount() == 0 && right_c.getConstCount() != 0)
            {
                System.out.println("No solution");
            }
            else
            {
                System.out.println("x=" + right_c.getConstCount() / left_x.getXCount());
            }
        }
        scanner.close();
    }
}
class Equation
{
    private String equation;
    private int xCount = 0;
    private int constCount = 0;
    public Equation(String equation)
    {
        this.equation = equation;
    }
    public void parse()
    {
        String[] expresses = equation.split("[-+]");
        String operators = equation.replaceAll("[^-+]", "");
        if(equation.charAt(0) != '-')
        {
            operators = "+" + operators;
        }
        for(int i = 0, j = 0; i < expresses.length; ++i, ++j)
        {
            if(expresses[i].equals(""))
            {
                j -= 1;
                continue;
            }
            char operator = operators.charAt(j);
            String express = expresses[i];
            int flag = operator == '-' ? -1 : 1;
            String filteredExpress = express.replaceAll("[^\\d]", "");
            if(filteredExpress.equals(""))
            {
                filteredExpress = "1";
            }
            int val = Integer.parseInt(filteredExpress);
            if(express.contains("x"))
            {
                this.xCount += flag * val;
            }
            else
            {
                this.constCount += flag * val;
            }
        }
    }
    public int getXCount()
    {
        return xCount;
    }
    public int getConstCount()
    {
        return constCount;
    }
    public void setXCount(int x)
    {
        this.xCount = x;
    }
    public void setConstCount(int count)
    {
        this.constCount = count;
    }
}
