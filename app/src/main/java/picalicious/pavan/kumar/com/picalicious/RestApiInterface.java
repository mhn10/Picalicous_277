package picalicious.pavan.kumar.com.picalicious;



import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Prajwal on 10/04/17.
 */

public interface RestApiInterface {

    @POST("uploadImages")
    Call<DiaryResponse> uploadImages(@Body HashMap<String, String> details);


//    @GET("users/{phoneNumber}/find")
//    Call<UserEntryResponse> getUserAuthStatus(@Path("phoneNumber") String phoneNumber);

}