package ru.geekbrains.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.exceptions.ResourceNotFoundException;
import ru.geekbrains.boot.model.Student;
import ru.geekbrains.boot.repositories.StudentInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentInMemoryRepository studentInMemoryRepository;

    public Student findById(Long id) {
        return studentInMemoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This student does not exist - " + id));
    }

    public List<Student> findAll() {
        return studentInMemoryRepository.findAll();
    }

    public List<Student> findAll(Integer minScore, Integer maxScore) {
        List<Student> out = findAll();
        if (minScore != null) {
            out = out.stream().filter(s -> s.getScore() >= minScore).collect(Collectors.toList());
        }
        if (maxScore != null) {
            out = out.stream().filter(s -> s.getScore() <= maxScore).collect(Collectors.toList());
        }
        return out;
    }

    public Student saveOrUpdate(Student s) {
        return studentInMemoryRepository.saveOrUpdate(s);
    }

    public void deleteBydId(Long id) {
        studentInMemoryRepository.deleteById(id);
    }
}
