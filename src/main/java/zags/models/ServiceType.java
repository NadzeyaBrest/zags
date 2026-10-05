package zags.models;

public enum ServiceType {
    BIRTH("Регистрация рождения"),

    MARRIAGE("Регистрация брака"),

    DEATH("Регистрация смерти");

    public final String applicationType;


    ServiceType(String applicationType) {
        this.applicationType = applicationType;
    }
}
