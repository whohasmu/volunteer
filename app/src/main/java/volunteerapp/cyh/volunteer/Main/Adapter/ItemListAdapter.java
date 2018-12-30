package volunteerapp.cyh.volunteer.Main.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Object.Item;

public class ItemListAdapter extends BaseAdapter{
    ArrayList<Item> items;


    public ItemListAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);


            holder.NanmmbyNm = convertView.findViewById(R.id.Text_NanmmbyNm);
            holder.Name = convertView.findViewById(R.id.Text_Name);
            holder.StartDate = convertView.findViewById(R.id.Text_startDate);
            holder.EndDate = convertView.findViewById(R.id.Text_EndDate);

            holder.ProgrmSttusSe = convertView.findViewById(R.id.Text_ProgrmSttusSe);

            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        Item item = (Item)getItem(position);



        holder.NanmmbyNm.setText(item.getNanmmbyNm());
        holder.Name.setText(item.getProgrmSj());
        holder.StartDate.setText(item.getProgrmBgnde());
        holder.EndDate.setText(item.getProgrmEndde());

        if(item.getProgrmSttusSe().toString().equals("2")){
            holder.ProgrmSttusSe.setText("모집중");
            holder.ProgrmSttusSe.setTextColor(Color.GREEN);

        }else if(item.getProgrmSttusSe().toString().equals("3")){
            holder.ProgrmSttusSe.setText("모집마감");
            holder.ProgrmSttusSe.setTextColor(Color.RED);
        }

        return convertView;


    }

    public  class Holder{

        TextView ProgrmSttusSe;
        TextView NanmmbyNm;
        TextView Name;
        TextView StartDate;
        TextView EndDate;




    }
}
