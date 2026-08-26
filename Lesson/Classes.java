package Lesson;

public class Classes {

    
    public static class Point {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point other) {
            return Math.hypot(this.x - other.x, this.y - other.y);
        }
    }

    
    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(6, 8);
        System.out.println(p1.distance(p2));
    }
}