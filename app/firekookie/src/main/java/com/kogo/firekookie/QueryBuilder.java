package com.kogo.firekookie;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class QueryBuilder {

    public QueryBuilder() {}

    public Query orderByChild(DatabaseReference databaseReference, String field) {
        return databaseReference.child("user-posts").orderByChild(field);
    }
}