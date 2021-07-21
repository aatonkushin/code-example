package org.tonkushin.example.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.example.model.Profession;
import org.tonkushin.example.repository.ProfessionRepository;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
public class ProfessionServiceTest {
    @Test()
    public void saveNewProfession() {
        Profession profession = new Profession();
        profession.setName("Профессия");

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.save(profession)).thenReturn(profession);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThat(service.save(profession)).isEqualTo(profession);
    }

    @Test()
    public void saveNewProfessionWithoutNameShouldThrowException() {
        Profession profession = new Profession();

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.save(profession)).thenReturn(profession);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThatThrownBy(() -> service.save(profession))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Поле 'Наименование' не должно быть пустым");
    }

    @Test()
    public void findAllProfessions() {
        List<Profession> professions = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            Profession p = new Profession();
            p.setName(String.format("Профессия %d", i));
            professions.add(p);
        }

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.findAll()).thenReturn(professions);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThat(service.findAll().size()).isEqualTo(3);
    }
}
