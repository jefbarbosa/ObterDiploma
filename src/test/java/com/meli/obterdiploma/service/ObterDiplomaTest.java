package com.meli.obterdiploma.service;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.model.SubjectDTO;
import com.meli.obterdiploma.repository.IStudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ObterDiplomaTest {

    @Mock
    IStudentDAO studentDAOMock;

    @InjectMocks
    private ObterDiplomaService obterDiplomaService;

    private List<SubjectDTO> subjectDTOList;
    private StudentDTO studentDTO;

    @Test
    public void SizeDAOTest() {
        Mockito.when(studentDAOMock.count()).thenReturn(23);
        String test = obterDiplomaService.serviceCount();
        assertEquals(test, "Count is: 23");
    }

    @BeforeEach
    public void setCOnfig() {
        subjectDTOList = new ArrayList<>();
        subjectDTOList.add(new SubjectDTO("Matemática", 3.0));
        subjectDTOList.add(new SubjectDTO("Português", 4.0));

        studentDTO = new StudentDTO(3L,"Jimenez",null, 0.0, subjectDTOList);
    }

    @Test
    public void calculateAverageTest() {

        assertNotNull(studentDAOMock);
        Mockito.when(studentDAOMock.findById(3L)).thenReturn(studentDTO);
        StudentDTO studentDTO = obterDiplomaService.analyzeScores(3L);

        assertEquals(studentDTO.getAverageScore(),3.5);
    }

    @Test
    @Tag("GrretingMessage")
    public void podeMelhorarGreetingMessageTest() {

        Mockito.when(studentDAOMock.findById(Mockito.anyLong())).thenReturn(studentDTO);
        StudentDTO studentDTO = obterDiplomaService.analyzeScores(50L);

        assertEquals(studentDTO.getMessage(), "O aluno Jimenez obteve uma média de 3,5. Você pode melhorar.");

    }
}
