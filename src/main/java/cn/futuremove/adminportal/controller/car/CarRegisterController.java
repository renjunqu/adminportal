package cn.futuremove.adminportal.controller.car;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.futuremove.adminportal.util.http.*;

import sun.misc.BASE64Encoder;

import cn.futuremove.adminportal.controller.BaseController;
import cn.futuremove.adminportal.model.joyMove.JoyNcar;
import cn.futuremove.adminportal.model.joyMove.JoyUsers;
import cn.futuremove.adminportal.util.ApplicationContextUtil;
import cn.futuremove.adminportal.util.jdbc.SmartRowMapper;


@Controller
@RequestMapping("/carRegister")
public class CarRegisterController  extends BaseController {
	
	final static Logger logger = LoggerFactory.getLogger(CarRegisterController.class);
	
	@RequestMapping(value = "/ext/store", method = { RequestMethod.POST, RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE })
	public void carRegisterStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		 StringBuffer jb = new StringBuffer();
		 String line = null; 
		 
		 StringBuffer sqlQuery = null;
		 StringBuffer countSqlQuery = null;
		   String xaction = request.getParameter("xaction");
		   JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
		   
		   if(xaction!=null && xaction.equals("read")){
			   Integer start =Integer.valueOf( request.getParameter("start") );
		       Integer limit =Integer.valueOf(request.getParameter("limit"));
		       String query = request.getParameter("query");
		       
		       sqlQuery = new StringBuffer("select * from JOY_NCar");
		       
		       countSqlQuery = new StringBuffer("select count(*) from JOY_NCar");
		       if(query!=null && query.length()>0) {
			    	 sqlQuery.append(" where " +query);
			    	 countSqlQuery.append(" where " +query);
			      }
		       
		       sqlQuery.append(" limit ").append(start).append(",").append(limit);
		         int totalResult = jdbcTemplate.queryForInt(countSqlQuery.toString());
		       List<JoyNcar> carList = jdbcTemplate.query(sqlQuery.toString(), new SmartRowMapper<JoyNcar>(JoyNcar.class));
		       Map jsonMap = new HashMap();
		       jsonMap.put("root", carList);
		       jsonMap.put("total", totalResult);
		       writeJSON(response, jsonMap);  
		   } else if(xaction !=null && xaction.equals("destroy")){
			   Integer id =Integer.valueOf( request.getParameter("id") );
			    sqlQuery = new StringBuffer("select * from  JOY_NCar where id  = ");
				sqlQuery.append(id);
				List<JoyNcar> carList  = jdbcTemplate.query(sqlQuery.toString(), new SmartRowMapper<JoyNcar>(JoyNcar.class));
				JoyNcar car = carList.get(0);
			    //tell cloudmove
				String cmUrl = "http://123.57.151.176:8088/cloudmove/cm/vin/delete";
				String cmData = "vin="+car.getVinnum()+"&time="+System.currentTimeMillis();
				String result = HttpPostUtils.post(cmUrl, cmData);
				logger.debug("result of cloudmove is "+result);
			   //delete it  
			   String sql = new String("delete from JOY_NCar where id = ") + id;
		       jdbcTemplate.execute(sql);
		       Map jsonMap = new HashMap();
		       jsonMap.put("result",0);
		       writeJSON(response, jsonMap);  
		   } else if(xaction !=null && xaction.equals("update")){
				   String sql = new String("update JOY_NCar set ");
				   String[] fields = new String[] {"id", "licensenum","vinnum","type","vendor","tboxnum","productdate","buydate","enginenum","terminalnum","carinfostatus","carbackstatus","mileage","restrictdate","updatetime","remarks"};
				   for(String f:fields){
					   if(request.getParameter(f)!=null && String.valueOf(request.getParameter(f)).length() >0){
						    sql += (f + "= '" +  request.getParameter(f)+"' ,");
					   }
				   }
				   //remove the last ","
				   //sql = sql.sub
				   if(sql.charAt(sql.length()-1)==',') {
					   sql = sql.substring(0,sql.length()-1);
				   }
				   sql += "where id = " + request.getParameter("id");
			          jdbcTemplate.execute(sql);
			       Map jsonMap = new HashMap();
			       jsonMap.put("result",0);
			       writeJSON(response, jsonMap);  
		   } else if(xaction!=null && xaction.equals("insert")) {
			   String sql = new String("insert into  JOY_NCar  ");
			   String[] fields = new String[] {"licensenum","vinnum","type","vendor","tboxnum","productdate","buydate","enginenum","terminalnum","carinfostatus","carbackstatus","mileage","restrictdate","updatetime","remarks"};
			   String fieldStr = "(";
			   String valueStr = "(";
			   for(String f:fields){
				   if(request.getParameter(f)!=null && String.valueOf(request.getParameter(f)).length() >0){
					   fieldStr = fieldStr +   f+" ,";
					   valueStr = valueStr + "'" + request.getParameter(f)+"' ,";
				   }
			   }
			   //remove the last ','
			   if(fieldStr.charAt(fieldStr.length()-1)==',') {
				   fieldStr = fieldStr.substring(0,fieldStr.length()-1);
				   valueStr = valueStr.substring(0,valueStr.length()-1);
			   }
			   sql += fieldStr + ") values " + valueStr + ")";
			       jdbcTemplate.execute(sql);
		       //tell cloudmove
		        String cmUrl = "http://123.57.151.176:8088/cloudmove/cm/vin/regist";
			    String cmData = "vin="+request.getParameter("vinnum")+"&time="+System.currentTimeMillis();
				String result = HttpPostUtils.post(cmUrl, cmData);
				logger.debug("result of cloudmove is "+result);
		       Map jsonMap = new HashMap();
		       jsonMap.put("result",0);
		       writeJSON(response, jsonMap);  
		   } else {
			   System.out.println("action is "+xaction);
			   Map<String, String[]> parameters = request.getParameterMap();
			   System.out.println("show parameters: ");
			   
			   Iterator entries = parameters.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry entry = (Map.Entry) entries.next();
					System.out.println("name: "+entry.getKey().toString());
					System.out.println("value: "+String.valueOf(entry.getValue()));
				}
		   }
	}
	
	
	@RequestMapping(value = {"/key/generate","/key/pub"}, method = { RequestMethod.POST, RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE })
	public void keyOperation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String URI = request.getRequestURI();
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_joyMove");
		 String carId = request.getParameter("carId");
		 if(URI.contains("generate")) {
			 
			  KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
		      BASE64Encoder encoder = new BASE64Encoder();
		     keyPairGen.initialize(1024, new SecureRandom());  
		      KeyPair keyPair= keyPairGen.generateKeyPair();  
		      RSAPrivateKey privateKey= (RSAPrivateKey) keyPair.getPrivate(); 
		      RSAPublicKey  publicKey= (RSAPublicKey) keyPair.getPublic(); 
		      //System.out.println(((String)(encoder.encode(publicKey.getEncoded()))).length());
		      String pubKey = encoder.encode(publicKey.getEncoded());
		      String priKey = encoder.encode(privateKey.getEncoded());
		      
		      StringBuffer sqlQuery = new StringBuffer("update JOY_NCar set RSAPubKey = \'");
		      sqlQuery.append(pubKey+"\' , RSAPriKey = \'"+priKey + "\' where id = " + carId);
		      jdbcTemplate.execute(sqlQuery.toString());
		       Map jsonMap = new HashMap();
		       jsonMap.put("pub",pubKey);
		       jsonMap.put("pri",priKey);
		       jsonMap.put("reult",0);
		       writeJSON(response, jsonMap);  
		 } else if (URI.contains("pub")){
			 Map jsonMap = new HashMap();
			 StringBuffer sqlQuery = new StringBuffer("select * from  JOY_NCar where id  = ");
			 sqlQuery.append(carId);
			 List<JoyNcar> carList  = jdbcTemplate.query(sqlQuery.toString(), new SmartRowMapper<JoyNcar>(JoyNcar.class));
			 JoyNcar car = carList.get(0);
			 
			 String cmUrl = "http://123.57.151.176:8088/cloudmove/cm/vin/sendCert";//ConfigUtils.getPropValues("cloudmove.sendKey");
			 String timeStr = String.valueOf(System.currentTimeMillis());
			 String cmData = "vin="+car.getVinnum()+"&cert="+car.getRsaprikey()+"&time="+timeStr;
			 String result = HttpPostUtils.post(cmUrl, cmData);
			 logger.debug("result of cloudmove is "+result);
		     jsonMap.put("reult",0);
		     writeJSON(response, jsonMap);  
		 }
	
	}

}
