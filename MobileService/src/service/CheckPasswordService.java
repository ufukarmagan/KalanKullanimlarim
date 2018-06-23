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

import org.json.JSONObject;

import com.google.gson.Gson;

import connection.ConnectionController;
import model.Customer;
import oracle.jdbc.OracleTypes;

@Path("/CheckPasswordService/")
public class CheckPasswordService {
	
	@GET
	@Produces("application/json")
	@Path("{param}/{param2}")
	public Response getUser(@PathParam("param") String custID,@PathParam("param2") String custPswrd) throws Exception {
		Connection connection = null;
		CallableStatement cstmt = null;
		Response response = null;
		try {
			connection = ConnectionController.getDBConnection();
			cstmt = connection.prepareCall("{? = call CheckPassword(?,?)} ");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setString(2, custID);
			cstmt.setString(3, custPswrd);
			cstmt.executeQuery();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", cstmt.getString(1));
			
			response = Response.status(200).entity(jsonObject.toString()).build();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (cstmt != null)
				cstmt.close();
			if (connection != null)
				connection.close();
		}

		return response;

}}
