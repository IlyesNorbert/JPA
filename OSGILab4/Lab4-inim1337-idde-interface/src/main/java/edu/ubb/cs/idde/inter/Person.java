package edu.ubb.cs.idde.inter;


//@Entity
//@Table(name = "person")
public class Person implements java.io.Serializable{

	public String Name;
	public int Age;
	public String Address;
	public String PhoneNumber;
	public int id;


	public Person() {
		
	}
	
	
	public Person(String uname, int age, String phoneNumber, String address, int uid){
		this.Name = uname;
		this.Age = age;
		this.Address = address;
		this.PhoneNumber = phoneNumber;
		this.id = uid;
	}


	//@Id
	//@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//@Column(name = "Name", nullable = false, length = 50)
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	//@Column(name = "Age")
	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		this.Age = age;
	}
	//@Column(name = "Address", length = 50)
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}
	
	//@Column(name = "PhoneNumber", length = 50)
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}

	

}
