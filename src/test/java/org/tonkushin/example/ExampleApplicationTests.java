package org.tonkushin.example;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tonkushin.example.service.DepartmentServiceImpl;
import org.tonkushin.example.service.PersonServiceImpl;
import org.tonkushin.example.service.ProfessionServiceImpl;

@RunWith(Suite.class)
@SpringBootTest
@TestPropertySource("/test.properties")
@Suite.SuiteClasses({DepartmentServiceImpl.class, ProfessionServiceImpl.class, PersonServiceImpl.class})
class ExampleApplicationTests {

    @Test
    void contextLoads() {
    }

}
