package sara.kataf.javadevelopersfromhamburg;

//this class is for the example item of the users shown
//in the main activity

public class ExampleItem {
    private String userImageUrl;
    private String userLogin;
    private int userNumOfRepos;
    private String userCreatedDate;

    public ExampleItem(String imageUrl, String login, int numberOfRepos, String createdDate) {
        userImageUrl = imageUrl;
        userLogin = login;
        userNumOfRepos = numberOfRepos;
        userCreatedDate = createdDate;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public int getUserNumOfRepos() {
        return userNumOfRepos;
    }

    public String getUserCreatedDate() {
        return userCreatedDate;
    }
}