public class Teststudent {
   public static void main(String[] args) {
       Student std1= new Student();
       Student std2= new Student("George",45);
       Student std3= new Student("Paul",50);
       Student std4= new Student();
       std2.setName("Collins");
       std2.setAge(57);
       std3.setName("Bob");
       std4.setAge(57);
       std4.setName("Stacy");
       System.out.println("Student 1 has an age "+std1.getAge()+" and name "+std1.getName());
       System.out.println("Student 2 has an age "+std2.getAge()+" and name "+std2.getName());
       System.out.println("Student 3 has an age "+std3.getAge()+" and name "+std3.getName());
       if(std4.isSameAge(std2)){
           System.out.println("Yes Student 4 and Student 2 have the same age.");
       }
       if(std1.isSameAge(std3)){
           System.out.println("Yes Student 1 and Student 3 have the same age.");
       }
       else{
           System.out.println("Student 1 and Student 3 have different ages.");
       }
   }
}
