public class Student {
     private String name;
     private int age;

     public Student(){
         name="nobody";
         age=20;
     }
     public Student(String std_name, int std_age){
         name=std_name;
         age=std_age;
     }
     public void setName(String std_name){
         name=std_name;
     }
     public void setAge(int std_age){
         age=std_age;
     }
     public String getName(){
         return name;
     }
     public int getAge(){
         return age;
     }
     public boolean isSameAge(Student std){
         boolean status=false;
         if(age==std.age){
             status=true;
         }
         return status;
     }
}
