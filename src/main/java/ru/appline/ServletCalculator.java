package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns = {"/calculator"}
)
public class ServletCalculator extends HttpServlet {
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    ServletCalculator.Result result = new ServletCalculator.Result();

    public ServletCalculator() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        int a = jsonObject.get("a").getAsInt();
        int b = jsonObject.get("b").getAsInt();
        String math = jsonObject.get("math").getAsString();
        PrintWriter pw = response.getWriter();
        response.setContentType("application/json;charset=utf-8");
        byte var11 = -1;
        switch(math.hashCode()) {
            case 42:
                if (math.equals("*")) {
                    var11 = 2;
                }
                break;
            case 43:
                if (math.equals("+")) {
                    var11 = 0;
                }
            case 44:
            default:
                break;
            case 45:
                if (math.equals("-")) {
                    var11 = 1;
                }
        }

        switch(var11) {
            case 0:
                this.result.setResult(a + b);
                pw.print(this.gson.toJson(this.result));
                break;
            case 1:
                this.result.setResult(a - b);
                pw.print(this.gson.toJson(this.result));
                break;
            case 2:
                this.result.setResult(a * b);
                pw.print(this.gson.toJson(this.result));
                break;
            default:
                if (b == 0) {
                    pw.print("Can't divide by 0");
                } else {
                    this.result.setResult(a / b);
                    pw.print(this.gson.toJson(this.result));
                }
        }

    }

    public class Result {
        public int result;

        public Result() {
        }

        public int setResult(int result) {
            return this.result = result;
        }
    }
}
