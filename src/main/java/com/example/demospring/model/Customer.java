package com.example.demospring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed {max} characters")
    private String firstName;

    
	
	public Customer() {
        
    }

	@NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed {max} characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;



	public Customer(Long id,
			@NotBlank(message = "First name is required") @Size(max = 100, message = "First name must not exceed {max} characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(max = 100, message = "Last name must not exceed {max} characters") String lastName,
			@Email(message = "Invalid email format") String email,
			@NotBlank(message = "Address is required") String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    
}
