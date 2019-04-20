package android.example.caproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterModule extends RecyclerView.Adapter<AdapterModule.ViewHolder> {
    private List<Object> modules;
    private ItemClickListener mItemClickListener;
    public String moduleId;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView nameOfModule;
        public TextView nameOfCourse;
        public TextView lecturer;
        public TextView start;
        public TextView end;
        public View layout;
        public ImageView avatar;
        ItemClickListener itemClickListener;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nameOfModule = (TextView) v.findViewById(R.id.nameOfModule);
            nameOfCourse = (TextView) v.findViewById(R.id.nameOfCourse);
            lecturer = (TextView) v.findViewById(R.id.lecturer);
            start = (TextView) v.findViewById(R.id.start);
            end = (TextView) v.findViewById(R.id.end);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION ){
                            mItemClickListener.onListClick(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

    public AdapterModule(List<Object> dataset) {
        modules = dataset;

    }

    @Override
    public AdapterModule.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View moduleView = inflater.inflate(R.layout.module_layout, viewGroup, false);
        ViewHolder module = new ViewHolder(moduleView);
        return module;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Map module = (HashMap) modules.get(i);
        this.moduleId = module.get("Module_ID").toString();
        viewHolder.nameOfModule.setText(module.get("Module_Code") + "-" + module.get("Module_Name"));
        viewHolder.nameOfCourse.setText(module.get("Course") + "-" + module.get("Course_Intake"));
        viewHolder.lecturer.setText(module.get("Lecturer").toString());
        viewHolder.start.setText(module.get("From").toString());
        viewHolder.end.setText(module.get("To").toString());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




    }

    public interface ItemClickListener {
        void onListClick(int position);
    }

    public void setmItemClickListener(ItemClickListener listener) {

         mItemClickListener = listener;
    }

    public String getModuleId(){

        return this.moduleId;
    }




        @Override
        public int getItemCount() {
            return modules.size();
        }


    }
