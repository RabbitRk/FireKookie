package com.jikook.firekookie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kogo.firekookie.KookieInit;
import com.kogo.firekookie.OnKookieComplete;
import com.kogo.firekookie.OnKookieListener;
import com.kogo.firekookie.OnKookieNested;
import com.kogo.firekookie.OnKookieSnap;
import com.kogo.firekookie.OnKookieUpdate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
//import static com.kogo.firekookie.KookieInit.AUTO_KEY;

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

        KookieInit init = new KookieInit(this);

        init.childListener("User", new OnKookieListener() {
            @Override
            public void onAdded(DataSnapshot result, String child) {
                Log.i(TAG, "onAdded: "+result.toString()+"   "+child);
            }

            @Override
            public void onChanged(DataSnapshot result, String child) {
                Log.i(TAG, "onChanged: "+result.toString()+"   "+child);
            }

            @Override
            public void onRemoved(DataSnapshot result) {
                Log.i(TAG, "onRemoved: "+result.toString());
            }

            @Override
            public void onMoved(DataSnapshot result, String child) {
                Log.i(TAG, "onMoved: "+result.toString()+"   "+child);
            }

            @Override
            public void onCancelled(String child) {
                Log.i(TAG, "onCancelled: "+child);
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
//        Log.i(TAG, "onCreate: ");
//        KookieInit kr = new KookieInit(this);
//        String test1 = t1.getText().toString();
//        String test2 = t2.getText().toString();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id", test1);
//        map.put("name", test2);
        DatabaseReference  reference = FirebaseDatabase.getInstance().getReference();

        Query myTopPostsQuery = reference.child("User").child("user_id").limitToFirst(1);
        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i(TAG, "onChildAdded: "+dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        kr.fetchParent("User", new OnKookieSnap() {
//            @Override
//            public void onComplete(DataSnapshot result) {
//                for (DataSnapshot snapshot : result.getChildren()){
//                    Post user =  snapshot.getValue(Post.class);
//
//                    Log.i(TAG, "onComplete: "+snapshot.child("name"));
//                }
//            }
//            @Override
//            public void onError(String result) {
//                Log.i(TAG, "onError: "+result);
//            }
//        });

//        kr.parentChild(map, "User", new OnKookieComplete() {
//            @Override
//            public void onComplete(String result) {
//                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(String result) {
//                Log.i(TAG, "onError: "+result);
//                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//          kr.fetchChild("User", "user_id", new OnKookieSnap() {
//              @Override
//              public void onComplete(DataSnapshot result) {
//                  Log.i(TAG, "onComplete: "+result.toString());
//              }
//
//              @Override
//              public void onError(String result) {
//
//              }
//          });


//        User user = new User("kumar", "bob");
//
//        kr.nestedUpdate(user.to_map(), "User", "user_id", "username", new OnKookieNested() {
//
//            @Override
//            public void onNested(String result) {
//                Toast.makeText(MainActivity.this, "Nested", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(String result) {
//                Toast.makeText(MainActivity.this, "Not updated", Toast.LENGTH_SHORT).show();
//            }
//        });








    }
}
