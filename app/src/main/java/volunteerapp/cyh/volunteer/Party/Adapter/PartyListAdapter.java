package volunteerapp.cyh.volunteer.Party.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Party.Object.Party;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;


public class PartyListAdapter extends BaseAdapter{
    ArrayList<Party> List;

    public PartyListAdapter(ArrayList<Party> list) {
        this.List = list;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_party, parent, false);
            holder.Text_Title = convertView.findViewById(R.id.Text_Title);
            holder.Text_Date = convertView.findViewById(R.id.Text_Date);
            holder.Text_Comment_Num = convertView.findViewById(R.id.Text_Comment_Num);
            holder.Text_Writer = convertView.findViewById(R.id.Text_Writer);
            holder.Text_Hit = convertView.findViewById(R.id.Text_Hit);
            convertView.setTag(holder);

        }else{
            holder = (Holder)convertView.getTag();
        }

        Party party = (Party)getItem(position);

        holder.Text_Title.setText(party.getTitle());
        holder.Text_Date.setText(party.getWrite_date());

        holder.Text_Writer.setText(party.getWriter());
        holder.Text_Hit.setText(party.getViews().toString());

        Call<String> observ = RetrofitService.getInstance().getRetrofitRequest().getPartyCommentCount(party.getId());
        final Holder finalHolder = holder;
        observ.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){



                    Log.d("로그","댓글수받아오기 성공!" + response.body());
                    finalHolder.Text_Comment_Num.setText(response.body());



                }else{
                    Log.d("로그","(댓글수)음...");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("로그","댓글수받아오기 실패! : " + t);
            }
        });



        return convertView;
    }

    public  class Holder{

        TextView Text_Title;
        TextView Text_Date;
        TextView Text_Comment_Num;
        TextView Text_Writer;
        TextView Text_Hit;






    }
}
