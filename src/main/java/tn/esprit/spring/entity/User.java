package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "user")
public class User implements Serializable{

        private static final long serialVersionUID = -6236517548335858347L;
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;

	    private String firstName;
	    private String lastName;
	    private String email;
	    @Enumerated(EnumType.STRING)
	    private Role role;	

		private String username;
	    private String password;
		private float accBalance;

	    
		@OneToMany(mappedBy="user")
		private List <UserProductViews> UserProductsViews;
		
		
		@OneToMany(mappedBy="user")
		private List <UserProductCategoryViews> userProductCategoriesViews;
		
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Participation> participation;
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Notification> notification;
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Contribution> contribution;
	
		public User() {}

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
		public List<UserProductCategoryViews> getUserProductCategoriesViews() {
			return userProductCategoriesViews;
		}

		public void setUserProductCategoriesViews(List<UserProductCategoryViews> userProductCategoriesViews) {
			this.userProductCategoriesViews = userProductCategoriesViews;
		}
		
		public List<UserProductViews> getUserProductsViews() {
			return UserProductsViews;
		}
		public void setUserProductsViews(List<UserProductViews> userProductsViews) {
			UserProductsViews = userProductsViews;
		}
		
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public float getAccBalance() {
			return accBalance;
		}

		public void setAccBalance(float accBalance) {
			this.accBalance = accBalance;
		}

		public List<Participation> getParticipation() {
			return participation;
		}

		public void setParticipation(List<Participation> participation) {
			this.participation = participation;
		}

		public List<Notification> getNotification() {
			return notification;
		}

		public void setNotification(List<Notification> notification) {
			this.notification = notification;
		}

		public List<Contribution> getContribution() {
			return contribution;
		}

		public void setContribution(List<Contribution> contribution) {
			this.contribution = contribution;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", role=" + role + ", username=" + username + ", password=" + password + ", accBalance="
					+ accBalance + ", UserProductsViews=" + UserProductsViews + ", userProductCategoriesViews="
					+ userProductCategoriesViews + ", participation=" + participation + ", notification=" + notification
					+ ", contribution=" + contribution + "]";
		}
		

}
