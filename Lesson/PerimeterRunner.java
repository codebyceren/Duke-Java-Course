package Lesson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerimeterRunner {

    public double getPerimeter(Shape s) {
        //Start with totalPerim = 0
        double totalPerim = 0;

        //Start with prevPt = the lastPoint
        Point prevPt = s.getLastPoint();

        //For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {

            //Find the distance from prevPt pt to currPt,
            double currDist = prevPt.distance(currPt);

            //Update totalPerim to be totalPerim + currDist
            totalPerim = totalPerim + currDist;

            //Update prevPt to be currPt
            prevPt = currPt;
        }
        //TotalPerim is my answer
        return totalPerim;
    }

    // Reads shape coordinates from a txt file
    public Shape readShapeFromFile(String filepath) {
        Shape shape = new Shape();
        try (Scanner scanner = new Scanner(new File(filepath))) {
            while (scanner.hasNextDouble()) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                shape.addPoint(new Point(x, y));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
        }
        return shape;
    }

    public static void main(String[] args) {
        PerimeterRunner runner = new PerimeterRunner();

        // Test 1: Handcoded points
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(4, 0));
        triangle.addPoint(new Point(0, 3));

        double perimeter = runner.getPerimeter(triangle);
        System.out.println("Calculated Perimeter: " + perimeter);

        // Test 2: Text file (uncomment when exampleData.txt exists)
        // Shape fileShape = runner.readShapeFromFile("exampleData.txt");
        // System.out.println("File Perimeter: " + runner.getPerimeter(fileShape));
    }
}

// NON-PUBLIC class for single-file structure
class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { 
        return x; 
    }
    
    public double getY() { 
        return y; 
    }

    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

// NON-PUBLIC class for single-file structure
class Shape {
    private List<Point> points;

    public Shape() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public Iterable<Point> getPoints() {
        return points;
    }

    public Point getLastPoint() {
        if (points.isEmpty()) {
            return null;
        }
        return points.get(points.size() - 1);
    }
}