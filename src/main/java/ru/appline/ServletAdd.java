package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.appline.logic.Model;
import ru.appline.logic.User;

@WebServlet(
        urlPatterns = {"/add"}
)
public class ServletAdd extends HttpServlet {
    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();

    public ServletAdd() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();

        try {
            BufferedReader reader = request.getReader();

            String line;
            while((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception var12) {
            System.out.println("Error");
        }

        JsonObject jobj = (JsonObject)this.gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();
        User user = new User(name, surname, salary);
        this.model.add(user, this.counter.getAndIncrement());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(this.gson.toJson(this.model.getFromList()));
    }
}
