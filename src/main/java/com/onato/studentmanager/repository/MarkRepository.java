package com.onato.studentmanager.repository;

import com.onato.studentmanager.model.Marks;
import com.onato.studentmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.error.Mark;

public interface MarkRepository extends JpaRepository<Marks, Long> {

}
