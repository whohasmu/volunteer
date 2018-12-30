package volunteerapp.cyh.volunteer.QnA.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import volunteerapp.cyh.volunteer.QnA.Object.QnA;

import volunteerapp.cyh.volunteer.R;

public class QnAListAdapter extends BaseAdapter{
    ArrayList<QnA> QnAs;


    public QnAListAdapter(ArrayList<QnA> QnAs) {
        this.QnAs = QnAs;
    }

    @Override
    public int getCount() {
        return QnAs.size();
    }

    @Override
    public Object getItem(int position) {
        return QnAs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_qna, parent, false);




            holder.Text_Title = convertView.findViewById(R.id.Text_Title);
            holder.Text_Date_Q = convertView.findViewById(R.id.Text_Date_Q);
            holder.Text_isAnswer = convertView.findViewById(R.id.Text_isAnswer);
            holder.Text_Writer = convertView.findViewById(R.id.Text_Writer);
            holder.Text_Views = convertView.findViewById(R.id.Text_Views);


            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        QnA QnA = (QnA)getItem(position);


        holder.Text_Title.setText(QnA.getTitle());
        holder.Text_Date_Q.setText(QnA.getDate_Q());
        if(QnA.getIsanswer().equals("1")) {
            holder.Text_isAnswer.setText("답변 대기중");
            holder.Text_isAnswer.setTextColor(Color.GRAY);

        }else if(QnA.getIsanswer().equals("2")) {
            holder.Text_isAnswer.setText("답변완료");
            holder.Text_isAnswer.setTextColor(Color.GREEN);
        }
        holder.Text_Writer.setText(QnA.getWriter());
        holder.Text_Views.setText(QnA.getHit());






        return convertView;
    }

    public  class Holder{


        TextView Text_Title;
        TextView Text_Date_Q;
        TextView Text_isAnswer;
        TextView Text_Writer;
        TextView Text_Views;




    }
}
