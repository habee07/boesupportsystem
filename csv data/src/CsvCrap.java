import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Naeem Docrat on 05 Sep 2018.
 */
public class CsvCrap {



        public static void main(String[] args) {
            ArrayList Courses = new ArrayList();

            String csvFile = "C:\\Users\\Naeem Docrat\\Desktop\\CSV FILES PROJECT\\reg1.csv";
            BufferedReader br = null;
            String line = "";

            try {
                FileWriter writer = new FileWriter("C:\\Users\\Naeem Docrat\\Desktop\\CSV FILES PROJECT\\mylove.csv");

                br = new BufferedReader(new FileReader(csvFile));

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] s1 = line.split(",,,");
                    String[] Course = s1[1].split(",");

                    String c1 = Course[0].substring(0,4);

                    if(!Courses.contains(c1)){

                        Courses.add(c1);
                        writer.append(c1+"\n");


                    }

                }
                writer.flush();
                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

