package com.kogo.firekookie;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class KookieInit {

    public static final int LIMIT_TO_FIRST = 1;
    public static final int LIMIT_TO_LAST = 10;
    public static final int ORDER_BY_CHILD = 101;
    public static final int ORDER_BY_VALUE = 102;
    public static final int ORDER_BY_KEY = 103;
    public static final int START_AT = 105;
    public static final int END_AT = 106;
    public static final int EQUAL_TO = 0;
    private static final String TAG = "Kookie";
    private DatabaseReference reference;
    Context context;

    public KookieInit(Context context) {
        this.context = context;
    }

    public void parentChild(HashMap<String, Object> map, String parent, String child, final OnKookieComplete listener) {
        reference = FirebaseDatabase.getInstance().getReference().child(parent).child(child);
        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: Inserted");
                    listener.onComplete("completed");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onError(e.getMessage());
            }
        });

    }

    public void parentChild(HashMap<String, Object> map, String parent, final OnKookieComplete listener) {
        reference = FirebaseDatabase.getInstance().getReference().child(parent);
        reference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: Inserted");
                    listener.onComplete("completed");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onError(e.getMessage());
            }
        });

    }

    public void childUpdate(HashMap<String, Object> map, String parent, String child, final OnKookieUpdate listener) {
        reference = FirebaseDatabase.getInstance().getReference().child(parent).child(child);
        reference.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: Inserted");
                    listener.onUpdate("completed");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onUpdate(e.getMessage());
            }
        });
    }

    public void nestedChild(HashMap<String, Object> map, String parent, String child, String field, final OnKookieNested listener)
    {
        reference = FirebaseDatabase.getInstance().getReference().child(parent).child(child).child(field);
        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: Inserted");
                    listener.onNested("completed");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onError(e.getMessage());
            }
        });
    }

    public void nestedUpdate(HashMap<String, Object> map, String parent, String child, String field, final OnKookieNested listener)
    {
        reference = FirebaseDatabase.getInstance().getReference().child(parent).child(child).child(field);
        reference.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: Inserted");
                    listener.onNested("completed");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onError(e.getMessage());
            }
        });
    }


    public void fetchParent(String parent, final OnKookieSnap listener)
    {
        reference = FirebaseDatabase.getInstance().getReference(parent);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onComplete(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getDetails());
            }
        });
    }

    public void fetchChild(String parent, String child, final OnKookieSnap listener)
    {
        reference = FirebaseDatabase.getInstance().getReference(parent).child(child);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onComplete(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getDetails());
            }
        });
    }

    public void childListener(String parent, final OnKookieListener listener)
    {
        reference = FirebaseDatabase.getInstance().getReference(parent);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listener.onAdded(dataSnapshot, s);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listener.onChanged(dataSnapshot, s);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                listener.onRemoved(dataSnapshot);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listener.onMoved(dataSnapshot, s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onCancelled(databaseError.getMessage());

            }
        });

    }

    public void queryParent(String parent, int flag, final OnKookieSnap listener)
    {
        reference = FirebaseDatabase.getInstance().getReference(parent);
        Query query = new QueryBuilder().orderByChild(reference,parent);
        switch(flag)
        {
            case LIMIT_TO_FIRST:

                break;
            case LIMIT_TO_LAST:

                break;
            case ORDER_BY_CHILD:

                break;
            case ORDER_BY_VALUE:

                break;
            case ORDER_BY_KEY:

                break;
            case START_AT:

                break;
            case END_AT:

                break;
            case EQUAL_TO:

                break;
        }
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onComplete(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getDetails());
            }
        });
    }
}