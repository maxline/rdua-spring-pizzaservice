package ua.rd.pizzaservice.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repoContext.xml"})
//там есть @Transactional и jdbc template  application context и logger
//..можем юзать во время тестов
public class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests{

}
