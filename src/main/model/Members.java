package model;

import model.exceptions.ExistingMemberException;
import model.exceptions.NotAMemberException;

import java.util.ArrayList;
import java.util.List;
// Sets up memberbase of academy, adds/removes students, gets students and lists students

public class Members {
    private List<Student> members;

    public Members() {
        members = new ArrayList<>();
    }

    // Modifies: This
    // Effects: Adds member to this

    public void addNewMember(Student s) throws ExistingMemberException {
        for (int i = 0; i < members.size(); i++) {
            if (s.equals(members.get(i))) {
                throw new ExistingMemberException();
            }
        }

        members.add(s);
    }
    // Modifies: This
    // Effects:  Removes member with specified name from this

    public void removeMember(String name) throws NotAMemberException {
        for (int i = 0; i < memberSize(); i++) {
            name = name.toLowerCase();
            if (name.equals(getMember(i).getName().toLowerCase())) {
                members.remove(i);
                return;

            }

        }
        throw new NotAMemberException();
    }

    // Modifies: Student Attendance
    // Effects: Adds 1 to attendance of specified student on specified day
/*
    public void attendMember(String name, int day) throws NotADayException, NotAMemberException {
        name = name.toLowerCase();
        for (int i = 0; i < memberSize(); i++) {
            if (name.equals(getMember(i).getName().toLowerCase())) {
                members.get(i).attend(day);
                return;
            }


        }
        throw new NotAMemberException();

    }
*/
    // Requires: this
    // effects: Returns member with given name
    public Student findMember(String name) throws NotAMemberException {
        name = name.toLowerCase();
        for (int i = 0; i < memberSize(); i++) {
            if (name.equals(getMember(i).getName().toLowerCase())) {
                return getMember(i);
            }
        }
        throw new NotAMemberException();
    }


    // Requires: This
    // Effects: Returns length of members
    public int memberSize() {
        return members.size();
    }


    //requires: This
    // Effects: Returns given student from member base
    public Student getMember(int i) {
        return members.get(i);

    }


    // template taken from my assignment two submission
    //   https://github.students.cs.ubc.ca/orgs/cpsc210-2019w-t2/teams/assign2_u4a3b
    // Requires: members.size >= 0
    // Effects: Lists out members details
    public String listMembers() {
        StringBuilder total = new StringBuilder();
        for (int i = 0; i < members.size(); i++) {
            total.append(Global.namefield + ":" + members.get(i).getName() + " "
                    + Global.agefield + ":" + members.get(i).getAge() + " " + Global.beltfield + ":"
                    + members.get(i).getBelt() + " " + Global.attendfield + ":"
                    + members.get(i).getAttendanceSheet() + System.lineSeparator());
        }
        return total.toString();
    }
}





