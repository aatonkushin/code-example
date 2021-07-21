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
import java.util.Optional;
import java.util.Random;

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
    public void saveNewProfessionWithLargeNameShouldThrowException() {
        Profession profession = new Profession();
        profession.setName(generateString(257));

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.save(profession)).thenReturn(profession);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThatThrownBy(() -> service.save(profession))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Поле 'Наименование' не должно превышать 255 символов");
    }

    @Test()
    public void saveNewProfessionWithLargeNotesShouldThrowException() {
        Profession profession = new Profession();
        profession.setName("Профессия");
        profession.setNotes(generateString(256));

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.save(profession)).thenReturn(profession);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThatThrownBy(() -> service.save(profession))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Поле 'Примечание' не должно превышать 255 символов");
    }

    public void saveNewProfessionWithExistingNameShouldThrowException() {
        String name = "Профессия";

        Profession profession = new Profession();
        profession.setName(name);

        Profession existingProfession = new Profession();
        profession.setId(1);
        profession.setName(name);

        Optional<Profession> existingOpt = Optional.of(existingProfession);


        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.findByName(name)).thenReturn(existingOpt);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThatThrownBy(() -> service.save(profession))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("Профессия с именем '%s' уже существует", profession.getName()));
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

    @Test
    public void findById() {
        Profession profession = new Profession();
        profession.setId(1);
        profession.setName("Профессия");

        Optional<Profession> opt = Optional.of(profession);

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.findById(1L)).thenReturn(opt);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThat(service.findOne(1L)).isEqualTo(profession);
    }

    @Test
    public void findByNotExistingIdShouldThrowException() {
        Profession profession = new Profession();
        profession.setId(1);
        profession.setName("Профессия");

        Optional<Profession> opt = Optional.of(profession);

        ProfessionRepository repository = Mockito.mock(ProfessionRepository.class);
        Mockito.when(repository.findById(1L)).thenReturn(opt);

        ProfessionService service = new ProfessionServiceImpl(repository);
        Assertions.assertThatThrownBy(() -> service.findOne(2L))
                .isInstanceOf(RuntimeException.class);
    }

    private String generateString(int targetStringLength) {
        int leftLimit = 97;     // 'a'
        int rightLimit = 122;   // 'z'

        Random random = new Random();

        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
