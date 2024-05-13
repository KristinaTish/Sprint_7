package courier;

public class Courier2 {
    private String login;
    private String password;

    public Courier2(String login, String password) {
        this.login = login;
        this.password = password;
    }
    // вытаскиваем из данных регистрации логин и пароль для логина
    public static Courier2 from(Courier courier){
        return new Courier2(courier.getLogin(), courier.getPassword());
    }

    public static Courier2 withLoginNull(Courier courier){
        return new Courier2(null, courier.getPassword());
    }

    public static Courier2 withoutLogin(Courier courier){
        return new Courier2("", courier.getPassword());
    }

    public static Courier2 withPasswordNull(Courier courier){
        return new Courier2(courier.getLogin(), null);
    }

    public static Courier2 withoutPassword(Courier courier){
        return new Courier2(courier.getLogin(), "");
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

