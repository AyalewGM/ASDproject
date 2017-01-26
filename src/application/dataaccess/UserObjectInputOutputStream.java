package application.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.business.*;

public class UserObjectInputOutputStream{
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "//src//application//dataaccess//storage//user.txt";
	
	public void addUser(List<UserDetails> users) throws ClassNotFoundException{
		try {
			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
//			List<UserDetails> list = new ArrayList();
//			list.add(user);

			output.writeObject(users);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public UserDetails checkUser(String uname, String upass){
		UserObjectInputOutputStream inputOutput = new UserObjectInputOutputStream();
		UserDetails user = inputOutput.getUsers(uname, upass);
		try{
			if (user.getUsername().equals(null)) {
				return null;
			} else {
				return user;
			}
		}catch(Exception e){
			return null;
		}
	}
	
	public UserDetails getUsers(String username, String password){
		try {
			//Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			List<UserDetails> users = (List<UserDetails>) input.readObject();
			
			UserDetails obj = new UserDetails();
			
			for(UserDetails details: users){
				if(username.equals(details.getUsername()) && password.equals(details.getPassword())){
					obj = details;
					System.out.println("UNSERNAME: " + details.getUsername() + " PASSWORD: " + details.getPassword() + " ROLE: " + details.getRole());
					return obj;
				}else{
					System.out.println("Invalid credentials");
				}
			}
			input.close();
			return obj;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
