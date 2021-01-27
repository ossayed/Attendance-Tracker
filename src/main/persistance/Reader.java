package persistance;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;
import java.util.List;

import model.exceptions.ExistingMemberException;
import model.Global;
import model.Members;
import model.Student;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;
// help from https://howtodoinjava.com/library/json-simple-read-write-json-examples/
// reads data from file

public class Reader {
    JSONParser info;
    Members newstus;


    //modifies: Data.json
    // effects reads data from disc
    public Members attendReader() {
        info = new JSONParser();
        newstus = new Members();
        try (FileReader reader = new FileReader("./data/data.json")) {

            Object obj = info.parse(reader);

            JSONArray studentList = (JSONArray) obj;


            for (int i = 0; i < studentList.size(); i++) {
                try {
                    newstus.addNewMember(parseStudentObject((JSONObject) studentList.get(i)));
                } catch (ExistingMemberException e) {
                    e.printStackTrace();
                }
            }


        } catch (FileNotFoundException  e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return newstus;
    }
    //requires: studentlist not empty
    //effects: makes new student from file

    private static Student parseStudentObject(JSONObject student) {

        String name = (String) student.get(Global.namefield);

        //Get employee last name
        Long age = (Long) student.get(Global.agefield);

        //Get employee first name
        String belt = (String) student.get(Global.beltfield);

        //Get employee last name
        List attendance = (List) student.get(Global.attendfield);

        Student newstu = new Student(name, Math.toIntExact(age), belt);
        newstu.attendance = attendance;
        return newstu;


    }
}





