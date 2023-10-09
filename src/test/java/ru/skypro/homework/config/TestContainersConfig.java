package ru.skypro.homework.config;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skypro.homework.containers.PostgresqlTestContainer;

@Testcontainers
public class TestContainersConfig {
    @Container
    public static PostgreSQLContainer container = PostgresqlTestContainer.getInstance();
}
