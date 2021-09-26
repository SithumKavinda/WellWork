package com.edu.wellwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddReview extends AppCompatActivity {

    Button buttonSubmit;
    RatingBar ratingbar;
    EditText UserName, UserReview;
    DatabaseReference reff;
    ReviewDet revdet;
    long maxid = 0;

    float myRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        UserName = (EditText) findViewById(R.id.UserName);
        UserReview = (EditText) findViewById(R.id.UserReview);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        ratingbar = findViewById(R.id.ratingBar2);
        revdet = new ReviewDet();
        reff = FirebaseDatabase.getInstance().getReference().child("ReviewDet");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int)v;
                String message = null;

                myRating = ratingBar.getRating();

                switch (rating) {
                    case 1:
                        message = "Sorry to here that";
                        break;
                    case 2:
                        message = "We always accept suggestions!";
                        break;
                    case 3:
                        message = "Good enough!";
                        break;
                    case 4:
                        message = "Great! Thank you!";
                        break;
                    case 5:
                        message = "Awesome! You are the best!";
                        break;
                }

                Toast.makeText(AddReview.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                revdet.setUserName(UserName.getText().toString().trim());
                revdet.setUserReview(UserReview.getText().toString().trim());

                reff.child(String.valueOf(maxid+1)).setValue(revdet);

                Toast.makeText(AddReview.this, "Data insert successfully", Toast.LENGTH_LONG).show();

                Toast.makeText(AddReview.this, "Your rating is: " + myRating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}