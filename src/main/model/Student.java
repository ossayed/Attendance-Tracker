package model;

import model.exceptions.NotADayException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// sets up students in academy, manages fields name,age,belt and attendance

public class Student {
    private String name;
    private int age;
    private String belt;
    public List<Integer> attendance;

    public Student(String name, int age, String belt) {
        this.name = name;
        this.age = age;
        this.belt = belt;
        attendance = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            attendance.add(0);
        }

    }
    // Requires: This
    // Effects: Returns this age

    public int getAge() {
        return age;

    }

    // Requires: This
    // Effects: returns this name
    public String getName() {
        return name;
    }

    // Requires: This
    // Effects: Returns this belt
    public String getBelt() {
        return belt;
    }

    // Requires: This
    // Effects: Returns this attendance
    public String getAttendanceSheet() {
        return (Arrays.toString(attendance.toArray()));
    }

    // Requires: This
    // Effects: Returns attendance.size()
    public int getAttendanceSheetLength() {
        return (attendance.size());
    }

    // Requires: This
    // Modifies: This
    // Effects: Adds one to specified day in index
    public void attend(int day) throws NotADayException {
        if (day <= 7) {
            attendance.add(day - 1, 1);
            attendance.remove(attendance.size() - 1);

        } else {
            throw new NotADayException();

        }
    }

    // requires this
    // modifies: nothing
    // effects returns attendance in type list
    public List<Integer> getAttendanceList() {
        return attendance;
    }

    //effects: compares if two students are equal
    @Override
    public boolean equals(Object o) {
        name = name.toLowerCase();
        Student student = (Student) o;
        return age == student.age
                &&
                name.equals(student.name.toLowerCase());

    }


}
