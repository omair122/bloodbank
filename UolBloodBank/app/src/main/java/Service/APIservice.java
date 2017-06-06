package Service;

import com.example.dev2.uolbloodbank.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by dev2 on 6/3/2017.
 */

public interface APIservice {


    @GET("public/donor")
    Call<List<User>> getDonors();

    @FormUrlEncoded
    @POST("public/donor")
    Call<User> InsertUser(@Field("name") String name, @Field("email") String email ,@Field("password") String password,@Field("BloodGroup") String BloodGroup , @Field("Phone") String Phone);


}
