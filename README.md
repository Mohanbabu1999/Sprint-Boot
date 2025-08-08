import java.util.Scanner;

public class NeonNumber
{
    public static void main(String args[])
    {
        //A number is declared
        int num = 9;

        //Square holds the square of the number
        int square = num*num , sum = 0, remainder;
        //Loop to iterate and add the digits of the square number
        while (square > 0)
        {
            remainder = square % 10;
            sum = sum + remainder;
            square = square / 10;
        }
        if (num == sum)
        {
            System.out.println(num+" is a Neon Number");
        }
        else
        {
            System.out.println(num+" is not a Neon Number");
        }
    }
}
