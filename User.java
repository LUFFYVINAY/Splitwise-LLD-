package Machine.splitwise;

public class User {
    private String Id;
    private String name;
    private String email;
    private String mobile;

    public User(String userId, String name, String email, String mobileNumber) {
        this.Id = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobileNumber;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobile;
    }


}
