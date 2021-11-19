package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.appline.logic.Model;
import ru.appline.logic.User;

@WebServlet(
        urlPatterns = {"/update"}
)
public class ServletUpdate extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();

    public ServletUpdate() {
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        JsonObject jsonObject = (JsonObject)this.gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();
        response.setContentType("application/json;charset=utf-8");
        if (id > 0) {
            if (id > this.model.getFromList().size()) {
                pw.print(this.gson.toJson("Такого пользователя нет"));
            } else {
                this.model.update(id, new User(name, surname, salary));
                pw.print("Updated successfully");
            }
        } else {
            pw.print(this.gson.toJson("ID должен быть больше нуля"));
        }

        pw.print(this.gson.toJson(this.model.getFromList()));
    }
}
