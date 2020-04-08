package com.jikook.firekookie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.kogo.firekookie.KookieInit;
import com.kogo.firekookie.OnKookieComplete;
import com.kogo.firekookie.OnKookieListener;
import com.kogo.firekookie.OnKookieNested;
import com.kogo.firekookie.OnKookieSnap;
import com.kogo.firekookie.OnKookieUpdate;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "rkd";
    EditText t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");

        KookieInit init = new KookieInit();

        /* you can directly call the function via class name
         * KookieInit.<function_name>()
         * */

        /*When new child is manipulated under the parent
         * You can call the single parent or else just give the path Eg. Users/username of the child
         * on which you are gonna add a ChildEventListener
         * */

        init.childListener("User", new OnKookieListener() {
            @Override
            public void onAdded(DataSnapshot result, String child) {
                Log.i(TAG, "onAdded: " + result.toString() + "   " + child);
            }

            @Override
            public void onChanged(DataSnapshot result, String child) {
                Log.i(TAG, "onChanged: " + result.toString() + "   " + child);
            }

            @Override
            public void onRemoved(DataSnapshot result) {
                Log.i(TAG, "onRemoved: " + result.toString());
            }

            @Override
            public void onMoved(DataSnapshot result, String child) {
                Log.i(TAG, "onMoved: " + result.toString() + "   " + child);
            }

            @Override
            public void onCancelled(String child) {
                Log.i(TAG, "onCancelled: " + child);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    public void testcode(View view) {

        KookieInit kr = new KookieInit();

        String test1 = t1.getText().toString();
        String test2 = t2.getText().toString();

        //Sample HashMap
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", test1);
        map.put("name", test2);

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

        /*
         * Updating the nested fields
         * */

        //Refer this Modal class too
        User user = new User("balaji", "kogo");

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
}
