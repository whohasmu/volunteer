package volunteerapp.cyh.volunteer.Home.FragView_HomeImage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import volunteerapp.cyh.volunteer.R;

public class view1 extends Fragment{

    private static view1 curr = null;

    public static view1 getInstance() {
        if (curr == null) {
            curr = new view1();
        }
        return curr;
    }

    ImageView img_view1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_home_view1,container,false);



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
