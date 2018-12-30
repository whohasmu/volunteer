package volunteerapp.cyh.volunteer.Notice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;
import volunteerapp.cyh.volunteer.R;

public class ImgFullActivity extends AppCompatActivity {

    ImageView imageView;
    PhotoViewAttacher photoViewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_full);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String ImgUrl = intent.getStringExtra("ImgUrl");

        Picasso.get().load(ImgUrl).into(imageView);

        photoViewAttacher = new PhotoViewAttacher(imageView);


    }
}
