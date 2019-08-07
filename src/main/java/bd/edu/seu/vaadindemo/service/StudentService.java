package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import bd.edu.seu.vaadindemo.repository.StudentRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService implements GenericService<Student, Long> {
    // TODO get rid of the repository

    private StudentRepository studentRepository;
    @Value("${baseUrl}/students")
    private String baseUrl;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        System.out.printf("Base URL: [%s]\n", baseUrl);
    }

    public Student findById(Long id) {
        System.out.printf("findById Base URL: [%s]\n", baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<Student> response = restTemplate.exchange(
                    baseUrl + "/" + id,
                    HttpMethod.GET,
                    null,
                    Student.class);
            Student student = response.getBody();
            System.out.println(student);
            return student;
        }
        catch(Exception e){
            return null;
        }
    }

    public List<Student> findAll() {
        System.out.printf("findAll Base URL: [%s]\n", baseUrl);
        //return studentRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>(){});
        List<Student> studentList = response.getBody();
        return studentList;
    }

    // TODO
    public Student save(Student student) {
      RestTemplate restTemplate = new RestTemplate();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Student> entity = new HttpEntity<Student>(student,headers);
//
//        ResponseEntity<Student> response = restTemplate.exchange(
//                baseUrl,
//                HttpMethod.POST,
//                entity,
//                Student.class);
//        Student postStudent = response.getBody();
//        System.out.println(postStudent);
//        return postStudent;
//
//        //return studentRepository.save(student);

        //works just fine
//        ResponseEntity<Student> response = restTemplate.exchange(
////                baseUrl,
////                HttpMethod.POST,
////                new HttpEntity<>(student),
////                Student.class);
//////Long.toString(updatedEmployee.getId()));
////        Notification.show("Main pera done");
////        Student editedStudent = response.getBody();
////        System.out.println(editedStudent);
////        return editedStudent;

        Student savedStudent = restTemplate.postForObject(baseUrl, student, Student.class);
        return savedStudent;
    }

    public ResponseEntity<Student> putWithExchange(Student updatedStudent) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(baseUrl ,
                HttpMethod.PUT,
                new HttpEntity<>(updatedStudent),
                Student.class
                );
    }
//    public void put(Student updatedStudent) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        restTemplate.put(baseUrl + "/{id}",
//                updatedStudent,
//                Long.toString(updatedStudent.getId()));
//    }

    public Student edit(Student student) {
        //RestTemplate restTemplate = new RestTemplate();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Student> entity = new HttpEntity<Student>(student,headers);
//        ResponseEntity<Student> response = restTemplate.exchange(
//                baseUrl,
//                HttpMethod.PUT,
//                entity,
//                Student.class);
//        Student putStudent = response.getBody();
//        System.out.println(putStudent);
//        return putStudent;

//    Map < String, String > params = new HashMap < String, String > ();
//        params.put("id", "1");
//    Employee updatedEmployee = new Employee("admin123", "admin123", "admin123@gmail.com");
//    RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedEmployee, params);

        //ResponseEntity<Student> response = restTemplete.exchange(baseUrl, HttpMethod.PUT, requestEntity, null, id);

//        try {
//                    HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Student> entity = new HttpEntity<Student>(student,headers);
//            ResponseEntity<Student> response = restTemplate.exchange(
//                    baseUrl+ "/" + student.getId(),
//                    HttpMethod.PUT,
//                    entity,
//                    Student.class);
//            Student editedStudent = response.getBody();
//            System.out.println(editedStudent);
//            return editedStudent;
//        }
//        catch (Exception e) {
//
//            return null;
//        }

//        RequestCallback requestCallback(final student updatedInstance) {
//            return clientHttpRequest -> {
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
//                clientHttpRequest.getHeaders().add(
//                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//                clientHttpRequest.getHeaders().add(
//                        HttpHeaders.AUTHORIZATION, "Basic " + getBase64EncodedLogPass());
//            };
//
//        Notification.show("Starting");
//        String resourceUrl =
//                baseUrl + '/' + student.getId();
//        HttpEntity<Student> requestUpdate = new HttpEntity<>(student);
//        Notification.show("request update done");
//        Notification.show(student.getName());
//        ResponseEntity<Student> response = restTemplate.exchange(
//                resourceUrl,
//                HttpMethod.PUT,
//                requestUpdate,
//                Student.class);
//        Notification.show("Main pera done");
//        Student editedStudent = response.getBody();
//        System.out.println(editedStudent);
//        return editedStudent;

//        ResponseEntity<Student> response = restTemplate.exchange(
//                baseUrl,
//                HttpMethod.POST,
//                new HttpEntity<>(student),
//                Student.class);
//Long.toString(updatedEmployee.getId()));
        Notification.show("Main pera start");
        //Student aStudent = new Student(1003l, "3nd Pupil", LocalDate.of(1980, Month.OCTOBER, 1));
        Student editedStudent = putWithExchange(student).getBody();
            Notification.show("Main pera done");
//        put(student);
//        System.out.println(editedStudent);
        return student;
    }


    // TODO
    public String delete(Student student) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", "2");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete ( baseUrl + "/" + student.getId(),  params );
        //studentRepository.delete(student);
        return null;
    }
}
