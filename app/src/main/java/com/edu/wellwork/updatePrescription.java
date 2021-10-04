package com.edu.wellwork;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updatePrescription {
    private DatabaseReference DbRef3;

    public updatePrescription(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DbRef3 = db.getReference(Prescription.class.getSimpleName());
    }

    public Task<Void> update (String key, HashMap<String ,Object> hashMap){
        return DbRef3.child(key).updateChildren(hashMap);
    }


}
