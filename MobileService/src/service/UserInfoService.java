package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import connection.ConnectionController;
import model.Customer;
import oracle.jdbc.OracleTypes;

@Path("/UserInfoService/")
public class UserInfoService {

	
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("{param}")
	public Response getUser(@PathParam("param") String custID) throws Exception {
		Connection connection = null;
		CallableStatement cstmt = null;
		Response response = null;
		ResultSet resultSet = null;
		List<Customer> list = new ArrayList<>();
		try {
			connection = ConnectionController.getDBConnection(); // ConnectionController sınıfı aracılığıyla bağlantı alındı.
			cstmt = connection.prepareCall("{? = call SendCustomerInfo(?)} "); // DB'den ilgili prosedür çağırılıyor. 2. soru işareti yerine msisdn yazılıyor, dönen cursor 1. soru işaretinin yerine atılıyor. 
			cstmt.registerOutParameter(1, OracleTypes.CURSOR); //DB'den alınacak değerin tipi belirtiliyor. 1. soru işareti gelen cursor.
			cstmt.setString(2, custID); //2. indis demek, 2. soru işareti yerine gelen parametreyi custID ye atar. yanı msisdn'i.
			cstmt.executeUpdate(); // statement perform ediliyor.
			
			// Cursoru alıp resultSete set ediyoruz.
			resultSet = (ResultSet) cstmt.getObject(1); // Cursor 1. soru işaretine karşılık geldiği için.

			// Biz sadece bir sonuc aldığımız için dögü yapmayadabilirdik. 
			// Ama birden fazla resultset döndüğü samanlarda döngü ile alınması lazım.
			while (resultSet.next()) {
				
				
				Customer customer = new Customer();
				customer.setFIRST_NAME(resultSet.getString("FIRST_NAME"));
				customer.setLAST_NAME(resultSet.getString("LAST_NAME"));
				customer.setTARIFF_NAME(resultSet.getString("TARIFF_NAME"));
				list.add(customer);
			} // scriptten gelen cursordaki değerler customer tipindeki liste atıldı.
			
			
			Gson gson = new Gson(); 
			String jsonCartList = gson.toJson(list); // listi json formatında parse etmek için gson kutuphanesinin toJson metodu kullanıldı.
		
			response = Response.status(200).entity(jsonCartList).build(); // uygulamaya dönecek response'a parse edilen json listi verildi.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (connection != null)
				connection.close();
		}

		return response;
	}
	

	
	
	/*
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("{param}")
	public Response getUser(@PathParam("param") String custID) throws Exception {
		String deger = "[{\"FIRST_NAME\":\"Atakan\",\"LAST_NAME\":\"Çoban\",\"TARIFF_NAME\":\"Bol Kepçe 3 GB Tarifesi\"}]";
		return Response.status(200).entity(deger).build();
	}*/
	
	
	
	
	
	

}
