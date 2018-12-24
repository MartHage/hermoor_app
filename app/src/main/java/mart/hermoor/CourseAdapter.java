package mart.hermoor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    private List<Course> entryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView course_code, course_name, date, description, result, weight;

        public MyViewHolder(View view) {
            super(view);
            course_code = (TextView) view.findViewById(R.id.course_code);
            course_name = (TextView) view.findViewById(R.id.course_name);
            description = (TextView) view.findViewById(R.id.description);
            result = (TextView) view.findViewById(R.id.result);
            weight = (TextView) view.findViewById(R.id.weight);
        }
    }

    public CourseAdapter(List<Course> entryList) {
        this.entryList = entryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course entry = entryList.get(position);
        System.out.println(entry.getCourse_code());
        holder.course_code.setText(entry.getCourse_code());
        holder.course_name.setText(String.format(" - %s", entry.getCourse_name()));
        holder.description.setText(entry.getDescription());
        holder.result.setText(entry.getResult());
        holder.weight.setText(String.format(" - %s%%", entry.getWeight()));
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }
}
