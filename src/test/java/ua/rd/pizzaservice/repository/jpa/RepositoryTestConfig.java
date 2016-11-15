package ua.rd.pizzaservice.repository.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repoContext.xml"})
//теперь создает все в памяти, и пропадает, не портит базу mysql
//@ContextConfiguration(locations = {"classpath:/repoContextForH2.xml"})
//там есть @Transactional и jdbc template  application context и logger
//..можем юзать во время тестов
//java.lang.Exception: No runnable methods
public abstract class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests {
}
