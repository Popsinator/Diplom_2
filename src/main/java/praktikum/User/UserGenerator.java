package praktikum.User;

public class UserGenerator {

    public static User getUser() {
        return new User("hellboy94@mail.ru", "qwertyboy", "hellboy");
    }

    public static User getUserIncorrect() {
        return new User("hellboy@mail.ru", "boy", "boy");
    }

    public static User getUserUpdate() {
        return new User("hell@mail.ru", "qwerty1234", "tratatata");
    }

    public static User getUserSecond() {
        return new User("hell@mail.ru", "qwerty1234", "tratatata");
    }
}
