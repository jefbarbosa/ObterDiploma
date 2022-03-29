package com.meli.obterdiploma.service;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.model.SubjectDTO;
import com.meli.obterdiploma.repository.IStudentDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ObterDiplomaTest {

    @Mock
    IStudentDAO studentDAO;

    @InjectMocks
    private ObterDiplomaService obterDiplomaService;

    @Test
    public void SizeDAOTest() {
        Mockito.when(studentDAO.count()).thenReturn(23);

        String test = obterDiplomaService.serviceCount();
        assertEquals(test, "Count is: 23");
    }

    @Test
    public void calculateAverageTest() {

        assertNotNull(studentDAO);

        List<SubjectDTO> subjectDTOList = new ArrayList<>();
        subjectDTOList.add(new SubjectDTO("Matemática", 3.0));
        subjectDTOList.add(new SubjectDTO("Português", 4.0));

        Mockito.when(studentDAO.findById(3L)).thenReturn(new StudentDTO(3L,"Jimenez",null, 0.0, subjectDTOList));

        StudentDTO studentDTO = obterDiplomaService.analyzeScores(3L);

        assertEquals(studentDTO.getAverageScore(),3.5);
    }
}
