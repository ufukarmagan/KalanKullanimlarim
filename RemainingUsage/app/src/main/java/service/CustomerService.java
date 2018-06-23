package service;

import java.util.List;

import model.Customer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by UFUK on 21.6.2017.
 */

public interface CustomerService  {

    @GET("UserInfoService/{param}") // web servisten gelen değer alınır.
    Call<List<Customer>> getCustomerInfo (@Path("param") String custID); //Customer tipindeki getCustomerInfo listesinde tutulur.

}
