package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testFindAll() {
        List<Student> studentList = studentService.findAll();
        assertNotNull(studentList);
        studentList.forEach(System.out::println);
    }

    // TODO add a test method for the save operation
    @Test
    public void testSave() {
        Student student = new Student(9999l, "Random Dude", LocalDate.of(1980, Month.OCTOBER, 30));
        Student expectedStudent = student;
        Student actualStudent = studentService.save(student);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void testFindById() {
        Student expectedStudent = new Student(9999l, "Random Dude", LocalDate.of(1980, Month.OCTOBER, 30));
        Student actualStudent = studentService.findById(9999l);
        assertEquals(expectedStudent, actualStudent);
    }

    // TODO add a test method for the delete operation
    @Test
    public void testDelete() {
        Student expectedStudent = new Student(9999l, "Random Dude", LocalDate.of(1980, Month.OCTOBER, 30));
        if(studentService.findById(9999l)==null)
            studentService.save(expectedStudent);

        String actualStudent = studentService.delete(expectedStudent);
        assertEquals(expectedStudent= null, actualStudent);
    }

}
