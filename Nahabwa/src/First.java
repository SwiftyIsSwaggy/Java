import java.util.Scanner;
public class First {
    public static void main(String[] args){
        int radius;
        int length;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the radius of the cylinder: ");
        radius=input.nextInt();
        System.out.println("Please enter the length of the cylinder: ");
        length=input.nextInt();
        double area=radius * radius * 3.14;
        double volume=area*length;
        System.out.println("The Volume is: "+ volume);
        System.out.println("The Area is :"+area);
        input.close();
    }
}
