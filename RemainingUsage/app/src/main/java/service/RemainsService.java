package service;

import java.util.List;

import model.Remains;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by UFUK on 22.6.2017.
 */

public interface RemainsService {
    @GET("RemainCallService/{param}")
    Call<List<Remains>> getCustomerRemains (@Path("param") String custID);
}
