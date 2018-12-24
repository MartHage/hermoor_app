package mart.hermoor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private List<Course> courseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;

    private CollectionReference eCollection = FirebaseFirestore.getInstance().collection("Grades");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        courseAdapter = new CourseAdapter(courseList);

        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(eLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(courseAdapter);

        fetchGrades();


        System.out.println(FirebaseInstanceId.getInstance().getToken());
    }

    public void fetchGrades() {
        eCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot snapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed:" + e);
                    return;
                }
                courseList.clear();

                for (DocumentSnapshot doc : snapshots) {
                    Set<String> keySet = doc.getData().keySet();
                    keySet.remove("course_name");

                    for (String key : keySet) {
                        HashMap<String, String> hash = (HashMap<String, String>)doc.getData().get(key);
                        Course course = new Course(doc.getId(), (String) doc.getData().get("course_name"), hash.get("date"), hash.get("description"), hash.get("result"), hash.get("weight"));
                        courseList.add(course);
                    }


                }
                courseAdapter.notifyDataSetChanged();
            }
        });

    }


}
