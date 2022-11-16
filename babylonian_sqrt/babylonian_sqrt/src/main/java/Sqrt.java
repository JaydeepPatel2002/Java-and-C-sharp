import java.util.Scanner;

public class Sqrt {
    public static  double sqrt(double x, double precision)
    {
        double guess = 0d;
        double oldGuess = 0d;
        if(x<0) return -1;
        guess = 1;
        boolean keepGoing = true;
        while(keepGoing)
        {
            oldGuess = guess;
            guess = (oldGuess+x/oldGuess)/2;
            if(Math.abs(oldGuess-guess)<=precision) keepGoing = false;
        }
        return guess;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double dVal = input.nextDouble();
        System.out.println(sqrt(dVal,1E-10));

    }
}
