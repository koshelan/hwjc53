import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "new_data.json";
        String json = readString(fileName);
        System.out.println("v1");
        List<Employee> list = jsonToList1(json);
        list.forEach(System.out::println);
        System.out.println("v2");
        list = jsonToList(json);
        list.forEach(System.out::println);
    }

    private static List<Employee> jsonToList1(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, new TypeToken<ArrayList<Employee>>() {
        }.getType());
    }

    private static String readString(String fileName) {
        String result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = br.readLine()) != null) {
                result += s;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonObjects = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            jsonObjects.forEach(jo -> list.add(gson.fromJson(jo.toString(), Employee.class)));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

}
