package com.example.roomdemo.fragmentPages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.roomdemo.MyRecycleViewAdapter;
import com.example.roomdemo.R;
import com.example.roomdemo.Student;
import com.example.roomdemo.StudentViewModel.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;


public class StudentsFragment extends Fragment {
    private StudentViewModel studentViewModel;
    private RecyclerView recyclerView;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Student>> filteredStu;


    public StudentsFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String pattern = s.trim();
                filteredStu.removeObservers(requireActivity());
                filteredStu = studentViewModel.findStuWithPattern(pattern);
                filteredStu.observe(requireActivity(), new Observer<List<Student>>() {
                    @Override
                    public void onChanged(List<Student> students) {
                        int temp = myRecycleViewAdapter.getItemCount();
                        myRecycleViewAdapter.setAllStudents(students);
                        if (temp != students.size()){
                            myRecycleViewAdapter.notifyDataSetChanged();
//                            myRecycleViewAdapter.notifyItemInserted(0);
                        }
                    }
                });
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.clear:
                AlertDialog.Builder builder= new AlertDialog.Builder(requireActivity());
                builder.setTitle("Cleat All Data????");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        studentViewModel.clearAllStudent();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        studentViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(StudentViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.sRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myRecycleViewAdapter = new MyRecycleViewAdapter(studentViewModel);
        recyclerView.setAdapter(myRecycleViewAdapter);

        filteredStu = studentViewModel.getGetAllStudents();
        filteredStu.observe(requireActivity(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                int temp = myRecycleViewAdapter.getItemCount();
                myRecycleViewAdapter.setAllStudents(students);
                if (temp != students.size()){
                    myRecycleViewAdapter.notifyDataSetChanged();
//                    myRecycleViewAdapter.notifyItemInserted(0);
                }

            }
        });
        floatingActionButton = requireActivity().findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_studentsFragment_to_addFragment);
        });

    }
}