package pl.jg.fchc.backend.it;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import pl.jg.fchc.backend.FchcBackendApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FchcBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///databasename",
})
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:3-alpine"))
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        redis.start();
        registry.add("spring.redis.host", redis::getContainerIpAddress);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }
}
