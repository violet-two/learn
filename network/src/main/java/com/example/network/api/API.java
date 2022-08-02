package com.example.network.api;

import com.example.network.domain.CommandItem;
import com.example.network.domain.GetWithParamsResult;
import com.example.network.domain.JsonResult;
import com.example.network.domain.PostFileResult;
import com.example.network.domain.PostWithParamsResult;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface API {
    @GET("/get/text")
    Call<JsonResult> getJson();

    @GET("/get/param")
    Call<GetWithParamsResult> getWithParams(@Query("keyword") String keyword,@Query("page") Integer page,@Query("order") String order);

    @GET("/get/param")
    Call<GetWithParamsResult> getWithParamsByMap(@QueryMap Map<String,Object> params);

    @POST("/post/string")
    Call<PostWithParamsResult> postWithParams(@Query("string") String content);
    @POST
    Call<PostWithParamsResult> postWithUrl(@Url String url);

    @POST("post/comment")
    Call<PostWithParamsResult> postWithBody(@Body CommandItem commandItem);

    @Multipart
    @POST("/file/upload")
    Call<PostFileResult> postFile(@Part MultipartBody.Part part);
}
