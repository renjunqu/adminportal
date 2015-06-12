package cn.futuremove.adminportal.controller.joymove;

import java.lang.reflect.Field;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymove.entity.JOYNCar;
import com.joymove.service.JOYNCarService;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.futuremove.adminportal.util.http.*;

import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;





@Controller
@RequestMapping("/carRegister")
public class CarRegisterController {

	@Resource(name = "JOYNCarService")
	private JOYNCarService joynCarService;

	
	final static Logger logger = LoggerFactory.getLogger(CarRegisterController.class);
	
	@RequestMapping(value = "/ext/store", method = { RequestMethod.POST, RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE })
	public @ResponseBody JSONObject carRegisterStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		   StringBuffer jb = new StringBuffer();
		   String line = null;
		   String xaction = request.getParameter("xaction");
		   Map<String,Object> likeCondition = new HashMap<String, Object>();
		   JSONObject Reobj = new JSONObject();
		   Reobj.put("result","10001");
		   JOYNCar ncarFilter = new JOYNCar();

		   if(xaction!=null && xaction.equals("read")){

			   Integer start = Integer.valueOf(request.getParameter("start"));
			   Integer limit = Integer.valueOf(request.getParameter("limit"));
			   List<JOYNCar> joynCarList  = joynCarService.getNeededList(ncarFilter, start, limit);
			   Reobj.put("root", joynCarList);
			   likeCondition.remove("pageStart");
			   List<JOYNCar> joynCarAllList =joynCarService.getNeededList(ncarFilter, null, null);
			   Reobj.put("total",joynCarAllList.size());

		   } else if(xaction !=null && xaction.equals("destroy")){
			   Integer id =Integer.valueOf( request.getParameter("id") );
			   likeCondition.put("id",id);
			   ncarFilter.id = id;
			   List<JOYNCar> carList  = joynCarService.getNeededList(ncarFilter, 0, 1); //jdbcTemplate.query(sqlQuery.toString(), new SmartRowMapper<JOYNCar>(JOYNCar.class));
			   JOYNCar car = carList.get(0);
			    //tell cloudmove
				String cmUrl = "http://123.57.151.176:8088/cloudmove/cm/vin/delete";
				String cmData = "vin="+car.vinNum +"&time="+System.currentTimeMillis();
				String result = HttpPostUtils.post(cmUrl, cmData);
				logger.debug("result of cloudmove is "+result);
			   //delete it  
			   joynCarService.deleteByProperties(car);
		       Reobj.put("result",0);

		   } else if(xaction !=null && xaction.equals("update")){
			   JOYNCar car = new JOYNCar();
			   ncarFilter.id = Integer.parseInt(request.getParameter("id"));
			   Field[] nCar_fields = car.getClass().getFields();
			   for(Field ncar_f:nCar_fields) {
				   if(request.getParameter(ncar_f.getName())!=null &&
						   String.valueOf(request.getParameter(ncar_f.getName())).length()>0) {
					   ncar_f.set(car,request.getParameter(ncar_f.getName()));
				   }
			   }
			   joynCarService.updateRecord(car, ncarFilter);
			   Reobj.put("result",0);

		   } else if(xaction!=null && xaction.equals("insert")) {
			   JOYNCar car = new JOYNCar();
			   Field[] nCar_fields = car.getClass().getFields();
			   for(Field ncar_f:nCar_fields) {
				   if(request.getParameter(ncar_f.getName())!=null &&
						   String.valueOf(request.getParameter(ncar_f.getName())).length()>0) {
                        ncar_f.set(car,request.getParameter(ncar_f.getName()));
				   }
			   }
			   joynCarService.insertRecord(car);
			   String cmUrl = "http://123.57.151.176:8088/cloudmove/cm/vin/regist";
			   String cmData = "vin="+request.getParameter("vinnum")+"&time="+System.currentTimeMillis();
			   String result = HttpPostUtils.post(cmUrl, cmData);
			   logger.debug("result of cloudmove is "+result);
			   Reobj.put("result",0);

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
		return Reobj;
	}
	
	
	@RequestMapping(value = {"/key/generate","/key/pub"}, method = { RequestMethod.POST, RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE })
	public @ResponseBody JSONObject  keyOperation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String URI = request.getRequestURI();
		Map<String,Object> likeCondition = new HashMap<String, Object>();
		JOYNCar  car = new JOYNCar();
		JSONObject Reobj = new JSONObject();
		JOYNCar ncarFilter = new JOYNCar();
		Reobj.put("result","10001");
		ncarFilter.id = Integer.parseInt(String.valueOf(request.getParameter("carId")));
		if(URI.contains("generate")) {
			  KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
		      BASE64Encoder encoder = new BASE64Encoder();
		      keyPairGen.initialize(1024, new SecureRandom());
		      KeyPair keyPair= keyPairGen.generateKeyPair();  
		      RSAPrivateKey privateKey= (RSAPrivateKey) keyPair.getPrivate(); 
		      RSAPublicKey  publicKey= (RSAPublicKey) keyPair.getPublic(); 
		      car.RSAPriKey = encoder.encode(privateKey.getEncoded());
			  car.RSAPubKey = encoder.encode(publicKey.getEncoded());
		      joynCarService.updateRecord(car,ncarFilter);
		       Reobj.put("pub", car.RSAPubKey);
			   Reobj.put("pri",car.RSAPriKey);
			   Reobj.put("reult",0);
		 } else if (URI.contains("pub")){
			 List<JOYNCar> carList  = joynCarService.getNeededList(ncarFilter);
			 car = carList.get(0);
			 String cmUrl = "http://123.57.151.176:8088/cloudmove/cm/vin/sendCert";//ConfigUtils.getPropValues("cloudmove.sendKey");
			 String timeStr = String.valueOf(System.currentTimeMillis());
			 String cmData = "vin="+car.vinNum +"&cert="+car.RSAPubKey+"&time="+timeStr;
			 String result = HttpPostUtils.post(cmUrl, cmData);
			 logger.debug("result of cloudmove is "+result);
		     Reobj.put("reult",0);
		 }
	    return Reobj;
	}

}
