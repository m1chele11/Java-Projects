class Shape {
    int area(int length) {
        return 0;
    }
}

class Square extends Shape {
    int area(int length) {
        return length * length;
    }
}

class Cube extends Square {
   // TODO: Override the area method to calculate the surface area of a cube.
   // Use the super.area method to calculate the area of one face of the cube.
    int area(int length) {
        return 6 * super.area(length);
    }
}


public class ShapeTest {
    public static void main(String[] args) {
        Square square = new Square();
        Cube cube = new Cube();

        System.out.println("Area of square: " + square.area(5));
        System.out.println("Surface area of cube: " + cube.area(5));
    }
}
