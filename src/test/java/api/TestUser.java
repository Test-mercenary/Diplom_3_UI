package api;

public class TestUser {

    public String email;
    public String password;
    public String name;

    public TestUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static TestUser getRandomUser() {
        String uniquePart = String.valueOf(System.currentTimeMillis());

        return new TestUser(
                "ui-test-user-" + uniquePart + "@yandex.ru",
                "password123",
                "Test User"
        );
    }
}