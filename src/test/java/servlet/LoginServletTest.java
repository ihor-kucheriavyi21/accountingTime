package servlet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class LoginServletTest {
    @Test
    public void testLoginServlet() {
        open("http://localhost:8080/login");
        $(By.name("username")).val("ww");
        $(By.name("userpass")).val("123").pressEnter();
        $$("thead tr th" ).shouldHave(size(9));

    }
}
