package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    @Value
    public static class AuthInfo {
        private String id;
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        Faker faker = new Faker(new Locale("en"));

        String id = String.valueOf(faker.random().nextInt(10000));
        String login = faker.name().username();
        String password = "qwerty123";

        AuthInfo authInfo = new AuthInfo(id, login, password);

        SQLHelper.insertUsers(authInfo);

        return authInfo;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getCode(AuthInfo authInfo) {
        String code = SQLHelper.getVerificationCode(authInfo);

        return new VerificationCode(code);
    }

    public static VerificationCode getBadCode() {
        Faker faker = new Faker();

        return new VerificationCode(String.valueOf(faker.random().nextInt(111111, 999999)));
    }
}
