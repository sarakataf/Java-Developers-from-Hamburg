package sara.kataf.javadevelopersfromhamburg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //this method is for being able to transfer data from one activity to another
    void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    //constructor of list and context
    ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    //get and post data in mainactivity
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getUserImageUrl();
        String login = currentItem.getUserLogin();
        int numberOfRepos = currentItem.getUserNumOfRepos();
        String date = currentItem.getUserCreatedDate();

        holder.uTextViewLogin.setText("Login Name: "+ login);
        holder.uTextViewRepos.setText("Number of Repos: "+Integer.toString(numberOfRepos));
        holder.uTextViewDate.setText("Created at: "+date);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.uImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView uImageView;
        public TextView uTextViewLogin;
        public TextView uTextViewRepos;
        public TextView uTextViewDate;

        //variables to be shown in main activity
        public ExampleViewHolder(View itemView) {
            super(itemView);
            uImageView = itemView.findViewById(R.id.image_view);
            uTextViewLogin = itemView.findViewById(R.id.text_view_creator);
            uTextViewRepos = itemView.findViewById(R.id.text_view_likes);
            uTextViewDate = itemView.findViewById(R.id.text_view_date);

            //this method is for being able to transfer data from one activity to another
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}