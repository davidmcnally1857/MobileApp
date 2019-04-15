package android.example.caproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterModuleContent extends RecyclerView.Adapter<AdapterModuleContent.Viewholder> {

    private List<Object> modules;
    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView topic_name;
        public TextView subtopic_name;
        public TextView description;
        public View layout;

        public Viewholder(View v) {
            super(v);
            layout = v;
            topic_name = (TextView) v.findViewById(R.id.topic_name);
            subtopic_name = (TextView) v.findViewById(R.id.subtopic_name);
            description = (TextView) v.findViewById(R.id.description_name);


        }

    }

        @Override
        public AdapterModuleContent.Viewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View moduleView = inflater.inflate(R.layout.details_module, viewGroup, false);
            Viewholder module = new Viewholder(moduleView);
            return module;
    }

        public AdapterModuleContent(List<Object> dataset) {
        modules = dataset;

        }

        @Override
        public void onBindViewHolder(AdapterModuleContent.Viewholder viewholder, int i) {
         final Map module = (HashMap) modules.get(i);
            viewholder.topic_name.setText(module.get("Topic_Name").toString());
            viewholder.subtopic_name.setText(module.get("Subtopic_Name").toString());
              viewholder.description.setText(module.get("TopicDescription").toString());


        }

        @Override
        public int getItemCount() {
            return modules.size();
        }
    }



