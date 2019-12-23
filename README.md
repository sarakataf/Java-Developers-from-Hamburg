XING Android Coding Challenge
=============================

This challenge would give us an idea about your coding skills. You get access to this
repository for 24 hours.

Steps
-----

1. Clone this repository. Use it as your working directory.
2. Bootstrap a new Android app.
3. Request the GitHub API to show [Java developers from Hamburg][1] when the user first
   opens the app and parse the JSON response.
4. Display a list of search results with found users, each entry should show
    - user avatar
    - login name
    - number of repos
    - joined date
5. Request only 10 users at a time. Use an endless list with a load more mechanism. The
   load more should be triggered when the scrolling is close to reaching the end of the
   list. Check the [pagination documentation][2].
6. On a list item select, display the user profile. The profile should be loaded from cache and it
   should show
    - avatar
    - real name
    - company
    - location
    - email
    - following
    - followers
    - public repos
    - public gists
    - bio
    - link to blog (open in browser)

Additional notes
----------------

- Important for us is code efficiency, following of best practices & code readability.
- We would prefer if you follow the material design guidelines.
- Functionality above must be implemented using common architectural patterns (MVC, MVVM or MVP).
- The business logic should at least be tested by a minimal set of unit tests.
- You can use a reactive approach (Ex. RxJava).
- Make sure the app runs on a ICS+ device.
- If your API request limit exceeds, you can generate and use a personal access token
  [here](https://github.com/settings/applications) and add
`?access_token=<YOUR_ACCESS_TOKEN>` to the request URLs.
- If you have any final comments about your result please let us know via final_notes.txt
- If you want to use different branches, please make sure that they'll be merged into master branch when you'll finish the task.


  [1]: https://developer.github.com/v3/search/#search-users
  [2]: https://developer.github.com/v3/#pagination