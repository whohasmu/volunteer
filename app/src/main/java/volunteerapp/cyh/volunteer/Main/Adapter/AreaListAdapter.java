package volunteerapp.cyh.volunteer.Main.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Object.Area;


public class AreaListAdapter extends BaseAdapter{
    ArrayList<Area> List;


    public AreaListAdapter(ArrayList<Area> List) {
        this.List = List;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_area, parent, false);
            holder.Name = convertView.findViewById(R.id.Text_Name);
            convertView.setTag(holder);

        }else{
            holder = (Holder)convertView.getTag();
        }

        Area area = (Area)getItem(position);
        holder.Name.setText(area.getName());

        return convertView;
    }

    public  class Holder{

        TextView Name;





    }
}
