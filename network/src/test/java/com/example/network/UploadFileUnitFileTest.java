package com.example.network;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadFileUnitFileTest {
    @Test
    public void uploadFileTest() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\File\\1.txt");
        File file2 = new File("C:\\Users\\Administrator\\Desktop\\File\\2.txt");
        RequestBody requestBody = RequestBody.create(file1, MediaType.parse("text/plain"));
        MultipartBody builder = new MultipartBody.Builder().
                addFormDataPart("file1", file1.getName(), requestBody).
                addFormDataPart("file2", file2.getName(), requestBody).build();
        Request build = new Request.Builder().url("https://www.httpbin.org/post").post(builder).build();
        Call call = okHttpClient.newCall(build);
        Response response = call.execute();
        System.out.println(response.body().string());

    }
}
