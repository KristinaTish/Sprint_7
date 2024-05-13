package courier;


import org.apache.commons.lang3.RandomStringUtils;


    public class Courier {
        private String login;
        private String password;
        private String firstName;

        public Courier(String login, String password, String firstName) {
            this.login = login;
            this.password = password;
            this.firstName = firstName;
        }


        public static Courier random() {
            return new Courier(RandomStringUtils.randomAlphabetic(5, 15), "12345", "Sam");
        }

        public static Courier withNullLogin() {
            return new Courier(null, "12345", "Sam");
        }

        public static Courier withoutLogin() {
            return new Courier("", "12345", "Sam");
        }

        public static Courier withNullPassword() {
            return new Courier(RandomStringUtils.randomAlphabetic(5, 15), null, "Sam");
        }

        public static Courier withoutPassword() {
            return new Courier(RandomStringUtils.randomAlphabetic(5, 15), "", "Sam");
        }

        public static Courier withNullFirstName() {
            return new Courier(RandomStringUtils.randomAlphabetic(5, 15), "12345", null);
        }

        public static Courier withoutFirstName() {
            return new Courier(RandomStringUtils.randomAlphabetic(5, 15), "12345", "");
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }

