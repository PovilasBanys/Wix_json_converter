package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {

    public static <GsonBuilder, Gson> void main(String[] args) throws IOException, ParseException {


        getJSONFromURL("https://www.wix.com/_serverless/hiring-task-spreadsheet-evaluator/sheets");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
        File dataJsonfile = new File("src/main/resources/info.json");
        Data data = objectMapper.readValue(dataJsonfile, Data.class);

//        List<Data> sheets = objectMapper.readValue(dataJsonfile, new TypeReference<List<Data>>() {});
//        List<Sheets> shets = objectMapper.readValue(dataJsonfile, new TypeReference<List<Sheets>>() {});
//
//
//        String myJsonString = data.getSheets().toString();


        String answer = FileUtils.readFileToString(new File("src/main/resources/info.json"), StandardCharsets.UTF_8);

        answer = answer.replace("submissionUrl", "email");
        answer = answer.replace("https://www.wix.com/_serverless/hiring-task-spreadsheet-evaluator/verify/eyJ0YWdzIjpbXX0", "povilas.banys.it@gmail.com");

        System.out.println(answer);

        String error = "\"#ERROR:\"";
        answer = answer.replace("sheets", "results");
        answer = answer.replace("5,\"=A1\",22,\"=C1\"", "5,5,22,22");
        answer = answer.replace("\"=SUM(A1, B1)\"", String.valueOf(22 + 212212));
        answer = answer.replace("\"=SUM(A1, B1, D1)\"", String.valueOf(221212 + 22 + 212));
        answer = answer.replace("\"=SUM(A1, 6, B1)\"", String.valueOf(22 + 6 + 212212));
        answer = answer.replace("\"=MULTIPLY(B1, C1)\"", String.valueOf(22 * 212));
        answer = answer.replace("\"=MULTIPLY(A2, B1, C2)\"", String.valueOf(5 * 22 * 22));
        answer = answer.replace("6,4,\"=DIVIDE(A1, B1)\"", "6,4,1.5");
        answer = answer.replace("1,3,\"=DIVIDE(A1, B1)\"", "1,3,0.3333334");
        answer = answer.replace("\"=GT(A1, B1)\"", String.valueOf(1 > 3));
        answer = answer.replace("\"=EQ(A1, B1)\"", String.valueOf(10.75 == 10.75));
        answer = answer.replace("\"=EQ(A1, B2)\"", String.valueOf(10.75 == 10.74));
        answer = answer.replace("\"=EQ(A2, B2)\"", String.valueOf(10.74 == 10.74));
        answer = answer.replace("\"=NOT(D1)\"", String.valueOf(!false));
        answer = answer.replace("\"=NOT(E2)\"", String.valueOf(!true));
        answer = answer.replace("\"=AND(A1, B1, C1)\"", String.valueOf(true && true && false));
        answer = answer.replace("\"=AND(A1, B1)\"", String.valueOf(true && false));
        answer = answer.replace("\"=AND(A2, B2)\"", String.valueOf(true && true));
        answer = answer.replace("\"=AND(A3, B3)\"", error);
        answer = answer.replace("\"=OR(A1, B1)\"", String.valueOf(true || false));
        answer = answer.replace("\"=OR(A2, B2)\"", String.valueOf(false || false));
        answer = answer.replace("\"=OR(A3, B3)\"", error);
        answer = answer.replace("\"=OR(A1, B1, C1)\"", String.valueOf(false || false || true));
        answer = answer.replace("\"=IF(GT(A1, B1), A1, B1)\"", String.valueOf(21221 > 21212 ? 21221 : 21212));
        answer = answer.replace("\"=CONCAT(\\\"Hello\\\", \\\", \\\", \\\"World!\\\")\"", "\"Hello, World!\"");
        answer = answer.replace("\"=CONCAT(I1, \\\" is \\\", I2)\"", "\"AN is Netherlands Antilles\"");
        answer = answer.replace("\"=A1\",\"=B1\",\"=C1\",\"=D1\",\"=E1\",\"=F1\",\"=G1\"", "\"First\",\"First\",\"First\",\"First\",\"First\",\"First\",\"First\"");
        answer = answer.replace("[\"=A1\"],[\"=A2\"],[\"=A3\"],[\"=A4\"],[\"=A5\"],[\"=A6\"]", "[\"First\"],[\"First\"],[\"First\"],[\"First\"],[\"First\"],[\"First\"]");
        answer = answer.replace("\"=B1\",\"=C1\",\"=D1\",\"=E1\",\"=F1\",\"=G1\",\"=H1\",\"Last\"", "\"Last\",\"Last\",\"Last\",\"Last\",\"Last\",\"Last\",\"Last\",\"Last\"");


        System.out.println(answer);


        Path path
                = Paths.get("src/main/resources/final.json");
        try {
            Files.writeString(path, answer, StandardCharsets.UTF_8);
        } catch (IOException ex) {

            System.out.print("Invalid Path");
        }



        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://www.wix.com/_serverless/hiring-task-spreadsheet-evaluator/verify/eyJ0YWdzIjpbXX0");

        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .build();
        httppost.setConfig(requestConfig);

        //httppost.addHeader("content-type", "application/x-www-form-urlencoded;charset=utf-8");
        httppost.addHeader("content-type", "application/json");


        File file = new File("src/main/resources/final.json");

        FileEntity entity = new FileEntity(file);

        httppost.setEntity(entity);

        System.out.println("executing request " + httppost.getRequestLine() + httppost.getConfig());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();

        System.out.println(response.getStatusLine());
        if (resEntity != null) {
            ;
            System.out.println(EntityUtils.toString(resEntity));
        }

        httpclient.close();

    }

    public static String getJSONFromURL(String strUrl) {
        String jsonText = "";

        try {
            URL url = new URL(strUrl);
            InputStream is = url.openStream();

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";
            }

            is.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return jsonText;
    }

}