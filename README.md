# FireKookie

Simplified callback functions for the Firebase realtime database. 

## Getting Started

Before getting started include google-services.json file in to the app folder. 

### Installing

Implement the library in the gradle file

```
implementation 'com.github.RabbitRk:FireKookie:1.0.0'
``` 

## Running principle

Firebase dependencies are added in the library itself. So you don't need to include in your dependencies.

##But 

It is not a good practice. Since, this project is in the BETA, you have to add the dependencies in you root gradle.

## Code break down

### Initialization

```
KookieInit kr = new KookieInit();

or

//just use the instance of the class like

new KookieInit().//...
```

### Note:

##You can use either the single parent string or a path of the data.

### Sample functions given below 

``` 
        //Sample HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", test1);
        map.put("name", test2);

        //Refer this Modal class too
        User user = new User("balaji", "kogo"); 
```
### Note:

When you use the class instance to send the data instead of using Hashmap<>, please refer the code below.

```
Sample Class:

public class User {
    String address, username;

    public User(String address, String username) {
        this.address = address;
        this.username = username;
    }

    /*
    * I dont know how to convert a modal class object into a HashMap
    * I hope this method will helps
    * */
    public HashMap<String, Object> to_map()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("username", username);
        return result;

    }
}

```
```
        /*
         * Fetch data for a single the child
         * */
        kr.fetchParent("User", new OnKookieSnap() {
            @Override
            public void onComplete(DataSnapshot result) {
                for (DataSnapshot snapshot : result.getChildren()) {
                    Post user = snapshot.getValue(Post.class);

                    Log.i(TAG, "onComplete: " + snapshot.child("name"));
                }
            }

            @Override
            public void onError(String result) {
                Log.i(TAG, "onError: " + result);
            }
        });
```

```
        /*
         * Generate a unique key for the child
         * */
        kr.parentChild(map, "User", new OnKookieComplete() {
            @Override
            public void onComplete(String result) {
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String result) {
                Log.i(TAG, "onError: " + result);
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
```

```
        /*
         * User defined key for the child
         * */
        kr.parentChild(map, "User", "User1", new OnKookieComplete() {
            @Override
            public void onComplete(String result) {
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String result) {
                Log.i(TAG, "onError: " + result);
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
```

```
        /*
         * Updated the child fields.
         * Rest of the fields will remain the same.
         * */
        kr.childUpdate(map, "User", "user_name", new OnKookieUpdate() {
            @Override
            public void onUpdate(String result) {
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String result) {
                Log.i(TAG, "onError: " + result);
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
```

```
        /*
         * Get data from the parent using child node
         * */
        kr.fetchChild("User", "user_id", new OnKookieSnap() {
            @Override
            public void onComplete(DataSnapshot result) {
                Log.i(TAG, "onComplete: " + result.toString());
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String result) {
                Log.i(TAG, "onError: " + result);
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
```

```
        /*
         * Setting data to the child node. Eg. User have two phone numbers
         * */
        kr.nestedChild(map, "User", "user_name", "address", new OnKookieNested() {
            @Override
            public void onNested(String result) {
                Toast.makeText(MainActivity.this, "Nested child added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String result) {
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onError: " + result);
            }
        });
```

```
        /*
         * Updating the nested fields
         * */
 
        kr.nestedUpdate(user.to_map(), "User", "user_id", "username", new OnKookieNested() {

            @Override
            public void onNested(String result) {
                Toast.makeText(MainActivity.this, "Nested", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String result) {
                Toast.makeText(MainActivity.this, "Not updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
```
 
## Author

* **Balaji Rk** - (https://github.com/RabbitRk) 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
