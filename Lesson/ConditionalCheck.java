package Lesson;

public class ConditionalCheck {
    
    public static int f(int x, int y) {
        if(x < y){
            System.out.println("x < y");
            return y + x;
        }else{
            System.out.println("x >= y");
            if(x > 8) {
                return y + 7;
            }
        }
        return x - 2;


            
    }

    public static void main(String[] args) {
        
        int result = f(10, 5);
        System.out.println("Result: " + result);
    }

}
