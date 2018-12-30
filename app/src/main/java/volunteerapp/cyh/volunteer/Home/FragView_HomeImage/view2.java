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


public class view2 extends Fragment{

    private static view2 curr = null;

    public static view2 getInstance() {
        if (curr == null) {
            curr = new view2();
        }
        return curr;
    }

    ImageView img_view2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_home_view2,container,false);



        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}