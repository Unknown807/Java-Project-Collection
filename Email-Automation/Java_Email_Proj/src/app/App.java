package app;

import java.util.Calendar;

public class App {
    public static void main(String[] args) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.TUESDAY)
        {
            String user_email = System.getenv("MAIL_USER");
            String user_pass = System.getenv("MAIL_PASS");
            Email email_obj = new Email(user_email, user_pass);
            email_obj.startUp();
            email_obj.sendMsg();
        }
        else
        {
            System.exit(0);
        }
    }
}