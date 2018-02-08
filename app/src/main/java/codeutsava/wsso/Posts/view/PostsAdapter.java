package codeutsava.wsso.Posts.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codeutsava.wsso.Posts.model.ElementsData;
import codeutsava.wsso.Posts.model.PostsData;
import codeutsava.wsso.R;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private List<PostsData> dataList = new ArrayList<>();
    Context context;
    private LayoutInflater layoutInflater;

    public PostsAdapter(Context context1) {
        context = context1;
        layoutInflater = LayoutInflater.from(context1);
    }
    void setData(List<PostsData> data)
    {
        this.dataList = data;
    }


    @Override
    public PostsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = layoutInflater.inflate(R.layout.posts_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("adapter",""+dataList.size());
        PostsData postsData = dataList.get(position);
        Log.d("adapter",""+dataList.get(0).getAlert_level());
        holder.location_tv.setText(postsData.getAddress());
        List<String> qr_info= Arrays.asList(postsData.getAddress().split(","));
        holder.habitat_tv.setText(qr_info.get(0));
        holder.threat_tv.setText(postsData.getAlert_level());
        List<ElementsData> elementsData=postsData.getElements();
        for(int i=0;i<elementsData.size();i++)
        {
            if(i==0)
            {
                holder.iron_tv.setText(elementsData.get(0).getCount());
            }
            else if(i==1)
            {
                holder.arscenic_tv.setText(elementsData.get(1).getCount());
            }
            else if(i==2)
            {
                holder.fluorine_tv.setText(elementsData.get(i).getCount());
            }
            else if(i==3)
            {
                holder.salinity_tv.setText(elementsData.get(i).getCount());
            }
            else
            {
                holder.nitrate_tv.setText(elementsData.get(i).getCount()+"\n"+elementsData.get(i).getLimit());
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView iron_tv,salinity_tv,fluorine_tv,nitrate_tv,arscenic_tv,habitat_tv,location_tv,threat_tv;
        public MyViewHolder(View itemView){
            super(itemView);
            iron_tv=(TextView)itemView.findViewById(R.id.iron_tv);
            salinity_tv=(TextView)itemView.findViewById(R.id.salinity_tv);
            fluorine_tv=(TextView)itemView.findViewById(R.id.fluorine_tv);
            arscenic_tv=(TextView)itemView.findViewById(R.id.arscenic_tv);
            nitrate_tv=(TextView)itemView.findViewById(R.id.nitrate_tv);
            habitat_tv=(TextView)itemView.findViewById(R.id.habitation_tv);
            location_tv=(TextView)itemView.findViewById(R.id.location_tv);
            threat_tv=(TextView)itemView.findViewById(R.id.threat_tv);
        }
    }
}
