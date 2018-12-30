package volunteerapp.cyh.volunteer.Notice.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import volunteerapp.cyh.volunteer.Notice.Object.Board;
import volunteerapp.cyh.volunteer.R;

public class NoticeListAdapter extends BaseAdapter{
    ArrayList<Board> notices;


    public NoticeListAdapter(ArrayList<Board> notices) {
        this.notices = notices;
    }

    @Override
    public int getCount() {
        return notices.size();
    }

    @Override
    public Object getItem(int position) {
        return notices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notice, parent, false);




            holder.Text_Title = convertView.findViewById(R.id.Text_Title);
            holder.Text_Content = convertView.findViewById(R.id.Text_Content);
            holder.Text_Write_Date = convertView.findViewById(R.id.Text_Write_Date);

            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        Board notice = (Board)getItem(position);




        holder.Text_Title.setText(notice.getTitle());
        holder.Text_Content.setText(deleteTag(notice.getContent()));
        holder.Text_Write_Date.setText(notice.getWrite_date());

        return convertView;
    }

    public  class Holder{


        TextView Text_Title;
        TextView Text_Content;
        TextView Text_Write_Date;




    }

    public String deleteTag(String t){
        t = t.replaceAll("</p>","\n");
        t = t.replaceAll("&nbsp;"," ");
        t = t.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return t;


    }
}
