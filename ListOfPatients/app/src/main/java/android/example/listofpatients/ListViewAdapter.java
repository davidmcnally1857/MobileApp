package android.example.listofpatients;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    private List<Patient> patients;
    private OnListListener mOnListListener;



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView ward;
        public TextView bednumber;
        public View layout;
        OnListListener onListListener;



        public ViewHolder(View v,OnListListener onListListener) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            ward = (TextView) v.findViewById(R.id.ward);
            bednumber = (TextView) v.findViewById(R.id.bednumber);
            this.onListListener = onListListener;
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
          onListListener.onListClick(getAdapterPosition());
        }
    }

    public ListViewAdapter(List<Patient> dataset, OnListListener onListListener) {
        patients = dataset;
        this.mOnListListener = onListListener;

    }


    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View patientView = inflater.inflate(R.layout.layout_list, viewGroup, false);
        ViewHolder patient = new ViewHolder(patientView, mOnListListener);
        return patient;
    }



    @Override
    public void onBindViewHolder(ListViewAdapter.ViewHolder viewHolder, int i) {
        final Patient patient = patients.get(i);
        viewHolder.name.setText("FirstName: " + patient.getName());
        viewHolder.ward.setText("Ward: " + patient.getWard());
        viewHolder.bednumber.setText("BedNumber: " + patient.getBedNumber());

        }

    public interface OnListListener{
      void onListClick(int position);
    }

    @Override
    public int getItemCount() {
        return patients.size();

    }
}
