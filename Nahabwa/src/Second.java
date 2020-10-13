import java.util.Scanner;
public class Second {
    public static void main(String[] args) {
        int num;
        int num_mod; //the number after it has been modified
        int temp;
        int total=0;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the number: ");
        num=input.nextInt();
        num_mod=num;
        while (num_mod!=0){
            temp=num_mod%10;
            total+=temp;
            num_mod=num_mod/10;
        }
       System.out.println("The Sum of the number "+num+"'s digits is "+total);
       input.close();
    }
}