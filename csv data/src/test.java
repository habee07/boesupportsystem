/**
 * Created by Naeem Docrat on 05 Sep 2018.
 */
public class test {


    public static void main(String[] args) {

        String CourseTitle = "Visiting Lecturer's Topic";

        if(CourseTitle.contains("\'")){

           CourseTitle = CourseTitle.replace("\'","%");
            System.out.println("fffff");

            System.out.println(CourseTitle);

        }


    }
}
