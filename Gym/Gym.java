package Gym;

import GUI.HomePage;

public class Gym 
{
	public static void main(String[] args) {
		
		/* Login Instructions :
		 * 
		 * Login as manager : 
		 * username : admin			password : admin
		 * 
		 * Login as member without membership :
		 * member 12:
		 * username : 123456803     password: password
		 * 
		 * Login as member with membership and training sessions :
		 * member 1 : 
		 * username : 123336789     password : password
		 * 
		 * Login as Trainer with training sessions :
		 * trainer 1 
		 * username : 111111189      password : password
		 * 
		 * Login as Trainer without training sessions :
		 * trainer 4 :
		 * username : 113456792     password : password
		 * 
		 ** These are just examples from CreateDatabase.java , feel free to pick anything else.
		 */
		
		DataBase.loadDataFromFile();
        new HomePage();
    }
}
