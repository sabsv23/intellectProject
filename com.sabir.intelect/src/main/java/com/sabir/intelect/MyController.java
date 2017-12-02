package com.sabir.intelect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sabir.intelect.MyRepository.Repo;
import com.sabir.intelect.entity.UserEntity;
import com.sabir.intelect.model.Errors;
import com.sabir.intelect.model.RequestDTO;
import com.sabir.intelect.model.ResponseDTO;

@RestController
public class MyController {
	
/*	@Autowired
	Repo repo;*/
	
	ArrayList<UserEntity> userEnList=new ArrayList<UserEntity>();
	
	@RequestMapping(path="/sabir/intelect/create",method=RequestMethod.POST, produces="application/json")
	public ResponseDTO createUser(@RequestBody RequestDTO req){
		
		ArrayList<Errors> errorList;
		errorList=validateFields(req);
		if(errorList!=null && errorList.size()>0){
			ResponseDTO response=new ResponseDTO();
			response.setvalErrors(errorList);
			System.out.println("Validation error ");
			return response;
		}
		
		if(userEnList!=null){
			UserEntity en=new UserEntity();
			for(UserEntity enti:userEnList){
				if(null!=enti.getEmail() && enti.getEmail().equals(req.getEmail())){
					
					
					ResponseDTO response=new ResponseDTO();
					ArrayList<Errors> errorlist=new ArrayList<Errors>();
					response.setResMsg("User already exists with the given emailId :"+req.getEmail());
					response.setvalErrors(errorlist);
					System.out.println("already exist user ");
					return response;
					
				}
				
			}
			
		}
		
		UserEntity entity=new UserEntity();
		
		entity.setfName(req.getfName());
		entity.setlName(req.getlName());
		entity.setEmail(req.getEmail());
		entity.setBirthDate(req.getBirthDate());
		entity.setPinCode(req.getPinCode());
		entity.setUuid(req.getId());
		entity.setUserId(req.getEmail()+"_"+req.getId());
		entity.setIsActive("true");
		userEnList.add(entity);
		
		System.out.println("created");
		

		System.out.println("User created "+Long.valueOf(entity.getId()));
		
		ResponseDTO resp=new ResponseDTO();
		resp.setResMsg("New User had been created");
		resp.setUserId(entity.getUserId());
		resp.setvalErrors(errorList);
		resp.setActive(true);
		System.out.println("last");
		return resp;				
		
	}
	
	

	@RequestMapping(path="/sabir/intelect/update")
	public ResponseDTO update(@RequestBody RequestDTO req){
		
		
		UserEntity user_update=new UserEntity();
		
		ArrayList<Errors> errorList;
		errorList=validateFields(req);
		if(errorList!=null && errorList.size()>0){
			ResponseDTO response=new ResponseDTO();
			response.setvalErrors(errorList);
			System.out.println("Validation error ");
			return response;
		}
		
		UserEntity old;
		
		if(userEnList!=null){
			for(UserEntity en:userEnList){
				old=en;
				if(en.getUserId().equals(req.getUserId())){
					user_update=old;
					userEnList.remove(old);
					user_update.setPinCode(req.getPinCode());
					user_update.setBirthDate(req.getBirthDate());
					
					
					ResponseDTO resp=new ResponseDTO();
					resp.setResMsg("Your details have been updates successfully");
					resp.setUserId(user_update.getUserId());
					resp.setvalErrors(errorList);
					resp.setActive(true);
					System.out.println("Update Succesffully ");
					return resp;
				}		
			}
		}
		
		ResponseDTO resp=new ResponseDTO();
		resp.setResMsg("User doesnt exists");
		resp.setUserId(req.getUserId());
		resp.setvalErrors(errorList);
		System.out.println("User doesnt exists ");
		return resp;

	}
	
	
	
	
	@RequestMapping(path="/sabir/intelect/delete",produces="application/json",method=RequestMethod.POST)
	public ResponseDTO deleteUser(@RequestBody RequestDTO req){
		
		UserEntity old;
		UserEntity user_update=new UserEntity();
		
		ArrayList<Errors> errorList;
		errorList=validateFields(req);
		
		if(errorList!=null && errorList.size()>0){
			ResponseDTO response=new ResponseDTO();
			response.setvalErrors(errorList);
			System.out.println("Validation error ");
			return response;
		}
		
		if(userEnList!=null){
			for(UserEntity en:userEnList){
				old=en;
				if(en.getUserId().equals(req.getUserId())){
					user_update=old;
					userEnList.remove(old);
					user_update.setPinCode(req.getPinCode());
					user_update.setBirthDate(req.getBirthDate());
					user_update.setIsActive("false");
					userEnList.add(user_update);
					
					ResponseDTO resp=new ResponseDTO();
					resp.setResMsg("User has been deleted/deactivated");
					resp.setUserId(user_update.getUserId());
					resp.setvalErrors(errorList);
					System.out.println("Update Succesffully ");
					return resp;
				}	
				System.out.println(en.getUserId());
			}
		}
		
		ResponseDTO response=new ResponseDTO();
		Errors error=new Errors();
		ArrayList<Errors> errorlist=new ArrayList<Errors>();
		
		error.setMessage("Something went wrong not able to delete user :"+req.getEmail());
		errorlist.add(error);
		response.setvalErrors(errorlist);
		System.out.println("already exist user ");
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Errors> validateFields(RequestDTO req){
		
		ArrayList<Errors> errorList=new ArrayList<Errors>();
		
		String id=req.getId();
		String fname=req.getfName();
		String lName=req.getlName();
		String email=req.getEmail();
		String pincode=req.getPinCode();
		String birthDate=req.getBirthDate();
		
		String atPos="@";
		String dotPos=".";
		
		if(id==null || (id!=null && id.equals(""))){
			Errors err=new Errors();
			err.setField("id");
			err.setMessage("Id field should not be empty");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		if(fname==null || (fname!=null && fname.equals(""))){
			Errors err=new Errors();
			err.setField("fName");
			err.setMessage("fName field should not be empty");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		if(lName==null || (lName!=null && lName.equals(""))){
			Errors err=new Errors();
			err.setField("lName");
			err.setMessage("lName field should not be empty");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		if(email==null || (pincode!=null && email.equals(""))){
			Errors err=new Errors();
			err.setField("email");
			err.setMessage("email field should not be empty");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		if(email!=null && (email.indexOf(atPos)<=0 ||
				email.lastIndexOf(dotPos)<email.indexOf(atPos) || email.lastIndexOf(dotPos)>=email.length())){
			Errors err=new Errors();
			err.setField("email");
			err.setMessage("Invalid Email ID");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		if(pincode==null || (pincode!=null && pincode.equals("")) || pincode.length()<6){
			Errors err=new Errors();
			err.setField("pinCode");
			err.setMessage("Invalid pinCode or Pincode should not be empty");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		
		if(birthDate==null || (birthDate!=null && birthDate.equals(""))){
			Errors err=new Errors();
			err.setField("birthDate");
			err.setMessage("birthDate should not be empty");
			err.setCode("Empty / Null");
			errorList.add(err);
		}
		
		if(birthDate!=null){
			try{
				Date date;			
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				sdf.setLenient(false);
				sdf.parse(birthDate);
			}catch(Exception e){
				Errors err=new Errors();
				err.setField("birthDate");
				err.setMessage("Invalid Date / Invalid Date Format");
				err.setCode("Empty / Null");
				errorList.add(err);
			}
			
		}
		
		return errorList;
		
	}

}
