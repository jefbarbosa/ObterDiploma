package com.meli.obterdiploma.repository;

import com.meli.obterdiploma.model.StudentDTO;

public interface IStudentDAO {
    int count();
    void save(StudentDTO stu);
    boolean delete(Long id);
    boolean exists(StudentDTO stu);
    StudentDTO findById(Long id);
}