package ru.skypro.homework.containers;


import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlTestContainer extends PostgreSQLContainer<PostgresqlTestContainer> {

    public static final String IMAGE_VERSION = "postgres:15";

    public static final String DATABASE_NAME = "database_name";

    public static PostgresqlTestContainer container;

    public PostgresqlTestContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresqlTestContainer getInstance() {
        if (container == null) {
            container = new PostgresqlTestContainer()
                    .withDatabaseName(DATABASE_NAME);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB.URL", container.getJdbcUrl());
        System.setProperty("DB.PASSWORD", container.getPassword());
        System.setProperty("DB.USERNAME", container.getUsername());
    }

    @Override
    public void stop() {
        super.stop();
    }
}
