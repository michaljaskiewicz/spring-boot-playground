package pl.javamentor.springbootplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.javamentor.springbootplayground.user.domain.UserService;

@SpringBootTest
class SpringBootPlaygroundApplicationTests {

    @Autowired
    private UserService myService;

    @Test
    void contextLoads() {
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
    }

}
