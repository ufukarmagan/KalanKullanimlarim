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
import model.RemainUsage;
import oracle.jdbc.OracleTypes;

@Path("/RemainCallService/")
public class RemainCallService {
	
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("{param}")
	public Response getData(@PathParam("param") String custID) throws Exception {
		Connection connection = null;
		CallableStatement cstmt = null;
		Response response = null;
		ResultSet resultSet = null;
		List<RemainUsage> list = new ArrayList<>();
		try {
			connection = ConnectionController.getDBConnection();
			cstmt = connection.prepareCall("{? = call SendRemainUsage(?)} ");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, custID);
			cstmt.executeUpdate();
			
			// get cursor and cast it to ResultSet
			resultSet = (ResultSet) cstmt.getObject(1);

			// loop it like normal
			while (resultSet.next()) {
				
				RemainUsage remainUsage = new RemainUsage();
				remainUsage.setREMAIN_DATA(resultSet.getDouble("REMAIN_DATA"));
				remainUsage.setREMAIN_VOICE(resultSet.getInt("REMAIN_VOICE"));
				remainUsage.setREMAIN_SMS(resultSet.getInt("REMAIN_SMS"));
				remainUsage.setUSABLE_DATA(resultSet.getDouble("TARIFF_DATA"));
				remainUsage.setUSABLE_VOICE(resultSet.getInt("TARIFF_VOICE"));
				remainUsage.setUSABLE_SMS(resultSet.getInt("TARIFF_SMS"));
				remainUsage.setLAST_READ(resultSet.getDate("READ_DATE"));
				
				list.add(remainUsage);
			}
			
			
			Gson gson = new Gson(); 
			String jsonCartList = gson.toJson(list);
		
			response = Response.status(200).entity(jsonCartList).build();

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

}


/*
 @GET
	@Produces("application/json;charset=utf-8")
	@Path("{param}")
	public Response getUser(@PathParam("param") String custID) throws Exception {
		String deger = "[{\"REMAIN_DATA\":2.6,\"REMAIN_VOICE\":479,\"REMAIN_SMS\":98708,\"USABLE_DATA\":3.0,\"USABLE_VOICE\":600,\"USABLE_SMS\":99000}]";
		return Response.status(200).entity(deger).build();
	}
 */
