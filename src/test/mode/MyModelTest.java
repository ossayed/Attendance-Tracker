package model;

import model.exceptions.ExistingMemberException;
import model.exceptions.NotADayException;
import model.exceptions.NotAMemberException;
import persistance.Reader;
import persistance.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest{
    Student s1;

    @BeforeEach
    public void runBefore(){
        s1 = new Student("Jack", 18, "Blue");
    }
    @Test
    public void attendSheetTest(){
        assertEquals(7,s1.getAttendanceSheetLength());
        assertEquals("[0, 0, 0, 0, 0, 0, 0]", s1.getAttendanceSheet());

    }

    @Test
    public void addOneAttendTest(){
        try{s1.attend(1);
            assertEquals("[1, 0, 0, 0, 0, 0, 0]",s1.getAttendanceSheet());}
        catch (NotADayException e) {
            fail();
        }

    }
    @Test
    public void addMultipleAttendTest(){
        try{s1.attend(1);
        s1.attend(2);
        s1.attend(7);
        assertEquals("[1, 1, 0, 0, 0, 0, 1]",s1.getAttendanceSheet());}
        catch (NotADayException e) {
            fail();
        }

    }
}

class MembersTest{

    Student s1;
    Student s2;
    Student s3;
    Members m;


    @BeforeEach
    public void runBefore(){
        s1 = new Student("Gregory",33,"Blue");
        s2 = new Student("AJ",22,"Green");
        s3 = new Student("Caroll",18,"blue");
        m =  new Members();

    }

    @Test
    public void addOneMemberTest(){
        try{m.addNewMember(s1);
            assertEquals(1,m.memberSize());
            assertEquals(m.getMember(0).getName(), "Gregory");
            assertEquals(m.getMember(0).getAge(), 33);
            assertEquals(m.getMember(0).getBelt(), "Blue");
        }
        catch (ExistingMemberException e) {
            fail();
        }

    }
    @Test
    public void addMultipleMemberTest(){
        try{m.addNewMember(s1);
        m.addNewMember(s2);
        assertEquals(2,m.memberSize());
        assertEquals(m.getMember(0).getName(), "Gregory");
        assertEquals(m.getMember(1).getName(), "aj"); }
        catch (ExistingMemberException e) {
            fail();
        }


    }

    @Test
    public void removeOneMemberTest(){
        try{m.addNewMember(s1);
        m.removeMember("Gregory");
        assertEquals(0,m.memberSize());
        }
        catch (ExistingMemberException | NotAMemberException e) {
            fail();
        }


    }
    @Test
    public void removeMultipleMembersTest(){
       try{m.addNewMember(s1);
        m.addNewMember(s2);
        m.addNewMember(s3);
        m.removeMember("Caroll");
        m.removeMember("AJ");
        assertEquals(1,m.memberSize());}
       catch (ExistingMemberException |NotAMemberException e) {
           fail();
       }

    }

    @Test
    public void listMembersTest(){
        try{m.addNewMember(s1);
        m.addNewMember(s2);
        m.addNewMember(s3);
        assertEquals("Name:Gregory Age:33 Belt:Blue Attendance:[0, 0, 0, 0, 0, 0, 0]\n"
                + "Name:aj Age:22 Belt:Green Attendance:[0, 0, 0, 0, 0, 0, 0]\n"
                + "Name:caroll Age:18 Belt:blue Attendance:[0, 0, 0, 0, 0, 0, 0]\n",m.listMembers());}
        catch (ExistingMemberException e) {
            fail();
        }

    }

    @Test
    public void attendOneMembersTest(){
        try{m.addNewMember(s1);
            m.addNewMember(s2);
        m.findMember("Gregory").attend(1);
        assertEquals("[1, 0, 0, 0, 0, 0, 0]",m.getMember(0).getAttendanceSheet());
        m.findMember("AJ").attend(1);}
        catch (NotADayException e) {
            System.out.println("day");
            fail();
        }
        catch(ExistingMemberException e){
            System.out.println("Exist");
            fail();
        }
        catch(NotAMemberException e){
            System.out.println("not");
            fail();

        }

    }

    @Test
    public void attendMulttipleMembersTest(){
        try{m.addNewMember(s1);
        m.addNewMember(s2);
        m.addNewMember(s3);
        m.findMember("Gregory").attend(1);
        m.findMember("AJ").attend(1);
        m.findMember("Caroll").attend(1);
        assertEquals("[1, 0, 0, 0, 0, 0, 0]",s1.getAttendanceSheet());
        assertEquals("[1, 0, 0, 0, 0, 0, 0]",s2.getAttendanceSheet());
        assertEquals("[1, 0, 0, 0, 0, 0, 0]",s3.getAttendanceSheet());}
        catch (NotADayException | ExistingMemberException | NotAMemberException e) {
            fail();
        }


    }






}

class ReaderTest {

    Student s1;
    Student s2;
    Student s3;
    Members mem;
    Members m1;
    Writer wri;
    Reader red;


    @BeforeEach
    public void runBefore() {
        s1 = new Student("Gregory", 33, "Blue");
        s2 = new Student("AJ", 22, "Green");
        s3 = new Student("Caroll", 18, "blue");
        mem = new Members();
        m1 = new Members();

    }

    @Test
    public void readOneTest() {
        try{m1.addNewMember(s1);
        wri = new Writer();
        red = new Reader();
        wri.attendWriter(m1);
        mem = red.attendReader();
        assertEquals(1, mem.memberSize());
        assertEquals("Gregory", mem.getMember(0).getName());
        assertEquals("[0, 0, 0, 0, 0, 0, 0]",mem.getMember(0).getAttendanceSheet());}
        catch (ExistingMemberException e) {
            fail();
        }


    }

    @Test
    public void readMultiTest() {
        try{m1.addNewMember(s1);
        m1.addNewMember(s2);
        m1.addNewMember(s3);
        wri = new Writer();
        red = new Reader();
        wri.attendWriter(m1);
        mem = red.attendReader();

        assertEquals(3, mem.memberSize());
        assertEquals("Gregory", mem.getMember(0).getName());
        assertEquals("[0, 0, 0, 0, 0, 0, 0]",mem.getMember(0).getAttendanceSheet());
        assertEquals("aj", mem.getMember(1).getName());
        assertEquals("[0, 0, 0, 0, 0, 0, 0]",mem.getMember(1).getAttendanceSheet());}
        catch (ExistingMemberException e) {
            fail();
        }


    }
}


class WriterTest {

    Student s1;
    Student s2;
    Student s3;
    Members mem;
    Writer wri;


    @BeforeEach
    public void runBefore() {
        s1 = new Student("Gregory", 33, "Blue");
        s2 = new Student("AJ", 22, "Green");
        s3 = new Student("Caroll", 18, "blue");
        mem = new Members();

    }

    @Test
    public void writeOneTest() {
        try{mem.addNewMember(s1);
        wri = new Writer();
        assertEquals("[{\"Attendance\":[0,0,0,0,0,0,0],\"Belt\":\"Blue\",\"Age\":33,"
                + "\"Name\":\"Gregory\"}]", wri.attendWriter(mem));}
        catch (ExistingMemberException e) {
            fail();
        }

    }

    @Test
    public void writeMultiTest() {
        try{mem.addNewMember(s1);
        mem.addNewMember(s2);
        mem.addNewMember(s3);
        wri = new Writer();
        assertEquals("[{\"Attendance\":[0,0,0,0,0,0,0],\"Belt\":\"Blue\",\"Age\":33,\"Name\":\"Gregory\"},"
                + "{\"Attendance\":[0,0,0,0,0,0,0],"
                + "\"Belt\":\"Green\",\"Age\":22,\"Name\":\"aj\"},"
                + "{\"Attendance\":[0,0,0,0,0,0,0],"
                + "\"Belt\":\"blue\",\"Age\":18,\"Name\":\"caroll\"}]", wri.attendWriter(mem));}
        catch (ExistingMemberException e) {
            fail();
        }


    }
}

class ExceptionTests {
    Student s1;
    Student s2;
    Student s3;

    @BeforeEach
    public void runBefore() {
        s1 = new Student("Gregory", 33, "Blue");
        s2 = new Student("AJ", 22, "Green");
        s3 = new Student("Caroll", 18, "blue");


    }

    @Test
    public void addMemberExistException(){
        try {
            Members mem = new Members();
            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1 );
            assertEquals(mem.getMember(0).getName(),"Gregory");
            mem.addNewMember(s1);
            System.out.println(false);
            fail();
        } catch (ExistingMemberException e) {

        }
    }
    @Test
    public void addMemberNoException(){
        try {
            Members mem = new Members();
            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1 );
            assertEquals(mem.getMember(0).getName(),"Gregory");
        } catch (ExistingMemberException e) {
            fail();
        }
    }

    @Test
    public void removeMemberNotExistException(){
        try{
            Members mem = new Members();

            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1 );
            assertEquals(mem.getMember(0).getName(),"Gregory");
            mem.removeMember("aj");
            fail();



        }
        catch(ExistingMemberException e){

            fail();
        }
        catch(NotAMemberException e){

        }
    }

    @Test
    public void removeMemberNoException(){
        try{
            Members mem = new Members();

            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1 );
            assertEquals(mem.getMember(0).getName(),"Gregory");
            mem.removeMember("Gregory");
            assertEquals(mem.memberSize(), 0 );

        }
        catch(ExistingMemberException e){

            fail();
        }
        catch(NotAMemberException e){
            fail();

        }
    }



    @Test
    public void atttendMemberNotDayException(){
        try {
            Members mem = new Members();
            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1);
            assertEquals(mem.getMember(0).getName(), "Gregory");
            assertEquals("[0, 0, 0, 0, 0, 0, 0]", mem.getMember(0).getAttendanceSheet());
            mem.findMember("gregory").attend(8);
            fail();
        }

        catch(ExistingMemberException e){
            fail();
        }
        catch(NotAMemberException e){
            fail();
        }
        catch(NotADayException e){

        }

    }

    @Test
    public void atttendMemberNotMemberException(){
        try {
            Members mem = new Members();
            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1);
            assertEquals(mem.getMember(0).getName(), "Gregory");
            assertEquals("[0, 0, 0, 0, 0, 0, 0]", mem.getMember(0).getAttendanceSheet());
            mem.findMember("bob").attend(8);
            fail();
        }

        catch(ExistingMemberException e){
            fail();
        }
        catch(NotAMemberException e){

        }
        catch(NotADayException e){
            fail();

        }

    }


    @Test
    public void atttendMemberNoMemberException(){
        try {
            Members mem = new Members();
            mem.addNewMember(s1);
            assertEquals(mem.memberSize(), 1);
            assertEquals(mem.getMember(0).getName(), "Gregory");
            assertEquals("[0, 0, 0, 0, 0, 0, 0]", mem.getMember(0).getAttendanceSheet());
            mem.findMember("gregory").attend(1);
            assertEquals("[1, 0, 0, 0, 0, 0, 0]", mem.getMember(0).getAttendanceSheet());

        }

        catch(ExistingMemberException e){
            fail();
        }
        catch(NotAMemberException e){
            fail();

        }
        catch(NotADayException e){
            fail();

        }

    }





}


