package entityClasses;

/*******
 * <p> Title: User Class </p>
 * 
 * <p> Description: This User class represents a user entity in the system.  It contains the user's
 *  details such as userName, password, and roles being played. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * 
 */ 

public class User {
	
	/*
	 * These are the private attributes for this entity object
	 */
	private int id;
    private String userName;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredFirstName;
    private String emailAddress;
    private boolean adminRole;
    private boolean studentRole;
    private boolean reviewerRole;
    private boolean instructorRole;
    private boolean staffRole;
    
    
    /*****
     * <p> Method: User() </p>
     * 
     * <p> Description: This default constructor is not used in this system. </p>
     */
    public User() {
    	
    }

    
    /*****
     * <p> Method: User(String userName, String password, boolean r1, boolean r2,
     * 		boolean r3, boolean r4, boolean r5) </p>
     * 
     * <p> Description: This constructor is used to establish user entity objects. </p>
     * 
     * @param userName specifies the account userName for this user
     * 
     * @param password specifies the account password for this user
     * 
     * @param r1 specifies the the Admin attribute (TRUE or FALSE) for this user
     * 
     * @param r2 specifies the the Student attribute (TRUE or FALSE) for this user
     * 
     * @param r3 specifies the the Reviewer attribute (TRUE or FALSE) for this user
     * 
     * @param r4 specifies the the Instructor attribute (TRUE or FALSE) for this user
     * 
     * @param r5 specifies the the Staff attribute (TRUE or FALSE) for this user
     * 
     */
    // Constructor to initialize a new User object with userName, password, and role.
    public User(String userName, String password, boolean r1, boolean r2, boolean r3, boolean r4,
    		boolean r5) {
        this.userName = userName;
        this.password = password;
        this.adminRole = r1;
        this.studentRole = r2;
        this.reviewerRole = r3;
        this.instructorRole = r4;
        this.staffRole = r5;
    }
    
 // Overloaded Constructor to initialize a new User object with all the details.
    public User(int id, String userName, String password, String firstName, String middleName, String lastName, String preferredFirstName, String emailAddress,  boolean r1, boolean r2, boolean r3, boolean r4,
    		boolean r5) {
    	this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.preferredFirstName = preferredFirstName;
        this.emailAddress = emailAddress;
        this.adminRole = r1;
        this.studentRole = r2;
        this.reviewerRole = r3;
        this.instructorRole = r4;
        this.staffRole = r5;
    }
    
    public User(String username) {
    	this.userName = username;
        this.password = null;
        this.adminRole = false;
        this.studentRole = true;
        this.reviewerRole = false;
        this.instructorRole = false;
        this.staffRole = false;
    }

    
    /*****
     * <p> Method: void setAdminRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Admin role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Admin role.
     * 
     */
    // Sets the role of the Admin user.
    public void setAdminRole(boolean role) {
    	this.adminRole=role;
    }

    
    /*****
     * <p> Method: void setStudentRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Student role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Student role.
     * 
     */
    // Sets the role of the Student user.
    public void setStudentRole(boolean role) {
    	this.studentRole=role;
    }

    
    /*****
     * <p> Method: void setReviewerRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Reviewer role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Reviewer role.
     * 
     */
    // Sets the role of the Reviewer user.
    public void setReviewerRole(boolean role) {
    	this.reviewerRole=role;
    }

    
    /*****
     * <p> Method: void setInstructorRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Instructor role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Instructor role.
     * 
     */
    // Sets the role of the Instructor user.
    public void setInstructorRole(boolean role) {
    	this.instructorRole=role;
    }

    
    
    
    /*****
     * <p> Method: void setID(int id ) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the id.
    public void setId(int id) {
    	this.id = id;
    }
    
    
    
    /*****
     * <p> Method: void setuserName(String userName) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the userName.
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    
    /*****
     * <p> Method: void setStaffRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setPassword(String password) {
    	this.password = password;
    }
    
    
    /*****
     * <p> Method: void setStaffRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    
    /*****
     * <p> Method: void setMiddleName(String middleName )</p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setMiddleName(String middleName ) {
    	this.middleName = middleName;
    }
    
    
    /*****
     * <p> Method: void setlastName(String lastName) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    
    /*****
     * <p> Method: void setStaffRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setPreferredFirstName(String preferredFirstName) {
    	this.preferredFirstName= preferredFirstName;
    }
    
    
    /*****
     * <p> Method: void setStaffRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setEmailAddress(String emailAddress ) {
    	this.emailAddress = emailAddress;
    }
    
   
    
    
    /*****
     * <p> Method: void setStaffRole(boolean role) </p>
     * 
     * <p> Description: This setter defines the Staff role attribute. </p>
     * 
     * @param role is a boolean that specifies if this user in playing the Staff role.
     * 
     */
    // Sets the role of the Staff user.
    public void setStaffRole(boolean role) {
    	this.adminRole=role;
    }
   
    
    /*****
     * <p> Method: String getAdminRole() </p>
     * 
     * <p> Description: This getter returns the value of the Admin role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
     * 
     */
    // Gets the current value of the Admin role attribute.
    public String getUserName() { return userName; }

    
    /*****
     * <p> Method: String getStudentRole() </p>
     * 
     * <p> Description: This getter returns the value of the Student role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Student role attribute.
    public String getPassword() { return password; }

    
    /*****
     * <p> Method: String getAdminRole() </p>
     * 
     * <p> Description: This getter returns the value of the Admin role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Admin role attribute.
    public boolean getAdminRole() { return adminRole; }

    
    /*****
     * <p> Method: String getStudentRole() </p>
     * 
     * <p> Description: This getter returns the value of the Student role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Student role attribute.
	public boolean getStudentRole() { return studentRole; }

    
    /*****
     * <p> Method: String getReviewerRole() </p>
     * 
     * <p> Description: This getter returns the value of the Reviewer role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Reviewer role attribute.
    public boolean getReviewerRole() { return reviewerRole; }

    
    /*****
     * <p> Method: String getInstructorRole() </p>
     * 
     * <p> Description: This getter returns the value of the Instructor role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Instructor role attribute.
    public boolean getInstructorRole() { return instructorRole; }

    
    /*****
     * <p> Method: String getStaffRole() </p>
     * 
     * <p> Description: This getter returns the value of the Staff role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Staff role attribute.
    public boolean getStaffRole() { return staffRole; }
    

    /*****
     * <p> Method: String getFirstName() </p>
     * 
     * <p> Description: This getter returns the value of the Staff role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Staff role attribute.
    public String getFirstName() { return firstName; }
    
    
    /*****
     * <p> Method: String getLastName() </p>
     * 
     * <p> Description: This getter returns the value of the Staff role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Staff role attribute.
    public String getlastName() { return lastName; }
    
    
    /*****
     * <p> Method: String getMiddleName) </p>
     * 
     * <p> Description: This getter returns the value of the Staff role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Staff role attribute.
    public String getMiddleName() { return middleName; }
    
    
    /*****
     * <p> Method: String getPreferredFirstName() </p>
     * 
     * <p> Description: This getter returns the value of the Staff role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Staff role attribute.
    public String getPreferredFirstName() { return preferredFirstName; }
    
    
    /*****
     * <p> Method: String getStaffRole() </p>
     * 
     * <p> Description: This getter returns the value of the Staff role attribute. </p>
     * 
     * @return a String of "TRUE" or "FALSE" based on state of the attribute
	 *
     */
    // Gets the current value of the Staff role attribute.
    public String getEmailAddress() { return emailAddress; }
       
    
     /*****
     * <p> Method: int getNumRoles() </p>
     * 
     * <p> Description: This getter returns the number of roles this user plays (0 - 5). </p>
     * 
     * @return a value 0 - 5 of the number of roles this user plays
	 *
     */
    // Gets the current value of the Staff role attribute.
    public int getNumRoles() {
    	int numRoles = 0;
    	if (adminRole) numRoles++;
    	if (studentRole) numRoles++;
    	if (reviewerRole) numRoles++;
    	if (instructorRole) numRoles++;
    	if (staffRole) numRoles++;
    	return numRoles;
    }
}

