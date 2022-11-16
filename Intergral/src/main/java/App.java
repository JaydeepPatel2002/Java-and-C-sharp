public class App {
    public static void main(String[] args) {
        IFunction func1 = new Func1();
        System.out.println("Trying simple left rectangle rule for equation 1(a)");
        long time1 = System.currentTimeMillis(); // or System.nanoTime()

        func1.printTable(0.0,10.0,1.0);
        System.out.println(func1.leftRectRule(3.0,9.0,
                1E-6,20));
        System.out.println(func1.leftRectRule(0,    10.0,
                1E-6,35));
        long time2 = System.currentTimeMillis(); // or System.nanoTime()
        System.out.println("result found in " + (time2 - time1));
        time1 = System.currentTimeMillis(); // or System.nanoTime()
        System.out.println(func1.leftRectRuleEfficient(3.0,9.0,
                1E-6,20));
        System.out.println(func1.leftRectRuleEfficient(0,    10.0,
                1E-6,35));
        time2 = System.currentTimeMillis(); // or System.nanoTime()
        System.out.println("result found in " + (time2 - time1));

    }
}
