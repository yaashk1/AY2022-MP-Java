package edu.illinois.cs.cs124.ay2022.mp.activities;

import static edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication.CLIENT_ID;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;

public class AddPlaceActivity extends AppCompatActivity {
  private static final String TAG = AddPlaceActivity.class.getSimpleName();
  @SuppressWarnings("checkstyle:RegexpSingline")
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addplace);
    Intent launch = getIntent();
    Button cancelButton = findViewById(R.id.cancel_button);
    Intent returnToMain = new Intent(this, MainActivity.class);
    returnToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    cancelButton.setOnClickListener(v -> {
      startActivity(returnToMain);
    });
    Button saveButton = findViewById(R.id.save_button);
    saveButton.setOnClickListener(v -> {
      Bundle b = launch.getExtras();
      double lat = 0.0;
      double lon = 0.0;
      if (b != null) {
        lat = Double.parseDouble(launch.getStringExtra("latitude"));
        lon = Double.parseDouble(launch.getStringExtra("longitude"));
      }
      EditText mEdit = findViewById(R.id.description);
      String desc = mEdit.getText().toString();
      Place p = new Place(CLIENT_ID, "Mickey", lat, lon, desc);
      FavoritePlacesApplication favoritePlacesApplication = (FavoritePlacesApplication) getApplication();
      favoritePlacesApplication.getClient().postFavoritePlace(p, ResultMightThrow -> {});
      startActivity(returnToMain);
    });
  }
}
