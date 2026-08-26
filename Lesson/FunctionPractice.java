package Lesson;

public class FunctionPractice {

    public static int myFunction(int x, int y) {
        int z = 2 * x - y;
        return z * x;
    }

    public static int f(int n) {
        return 3 + myFunction(n, n + 1);
    }

    public static int g() {
        int a;
        a = myFunction(3, 7);
        int b = f(a * a);
        return b;
    }

    public static void main(String[] args) {
        int result = g();
        System.out.println("Sonuç: " + result); // Returns 75
    }
}