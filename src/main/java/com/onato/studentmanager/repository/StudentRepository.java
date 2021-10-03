package com.onato.studentmanager.repository;

import com.onato.studentmanager.dto.SubjectTopperDTO;
import com.onato.studentmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByRollNo(Long rollNo);

    @Query(value="SELECT a.id,a.name,a.class_name,a.roll_no,AVG(MARKS) as avg FROM STUDENTS a LEFT JOIN MARKS b on a.id = b.student_id group by a.id Having avg > ?1", nativeQuery=true)
    List<Student> findStudentsHavingAtLeastPercentage(int percent);

    @Query(value="SELECT X.SUBJECT, X.MARKS,X.STUDENT_ID,st.ROLL_NO,st.NAME,st.CLASS_NAME\n" +
            "FROM(\n" +
            "  SELECT *, ROW_NUMBER()OVER(PARTITION BY Subject ORDER BY Marks DESC) rn\n" +
            "    FROM MARKS\n" +
            ")X\n" +
            "LEFT JOIN STUDENTS st\n" +
            "on X.STUDENT_ID = st.ID\n" +
            "WHERE rn = 1", nativeQuery=true)
    List<SubjectTopperDTO> getSubjectToppers();
}
