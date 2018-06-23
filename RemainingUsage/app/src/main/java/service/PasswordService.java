package service;

import java.util.List;

import model.Login;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by UFUK on 23.6.2017.
 */

public interface PasswordService {
    @GET("CheckPasswordService/{param}/{param2}")
    Call <Login> getPasswordInfo (@Path("param") String custID , @Path("param2") String custPswrd);
}
