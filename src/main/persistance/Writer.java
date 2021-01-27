package persistance;

import model.Members;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import model.*;

//writes data to disc
// help from https://mkyong.com/java/json-simple-example-read-and-write-json/
public class Writer {

    // requires: members non empty
    // modifies data.json
    // effects: writes data to file
    public String attendWriter(Members members) {
        JSONObject allstuinfo = new JSONObject();
        JSONArray students = new JSONArray();


        for (int i = 0; i < members.memberSize(); i++) {
            JSONObject info = new JSONObject();
            info.put(Global.namefield, members.getMember(i).getName());
            info.put(Global.agefield, members.getMember(i).getAge());

            info.put(Global.beltfield, members.getMember(i).getBelt());

            JSONArray attend = new JSONArray();
            attend.addAll(members.getMember(i).getAttendanceList());


            info.put(Global.attendfield, attend);

           // allstuinfo.put("Student",info);
           // System.out.println(allstuinfo);
           // allstuinfo.put("Student",info);

            students.add(info);




        }



        try (FileWriter file = new FileWriter("./data/data.json")) {
            file.write(students.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (students.toString());
    }


}

