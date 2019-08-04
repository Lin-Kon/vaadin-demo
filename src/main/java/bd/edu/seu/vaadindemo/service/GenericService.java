package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;

import java.util.List;

public interface GenericService<T, I> {
    List<T> findAll();
    T findById(I i);
    Student save(T t);
    String delete(T t);

    // possibly many more
}
