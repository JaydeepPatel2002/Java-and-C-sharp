import java.util.Scanner;

public class App {

    public static void main(String[] args) {
//        System.out.println(axcos(0.785398,1e-4));
        IFunction func1 = new Function2();
        //func1.printTable(-0.5,0,0.05);
        System.out.print(func1.findZero(-0.35,-0.30,1E-5));
    }
    public static double axcos(double x, double precision)
    {
        //Assume x is radian
        x %= (Math.PI * 2d);
        double rs = 1d;
        double delta = 1d;
        for(int i=1;Math.abs(delta/rs)>precision && rs!=0;i++)
        {
            delta *= -1.0 * x * x /(2.0*i*(2.0 * i -1.0));
            rs+=delta;
        }
        return rs;
    }
}
