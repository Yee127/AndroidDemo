package com.example.roomdemo.fragmentPages;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdemo.R;
import com.example.roomdemo.Student;
import com.example.roomdemo.StudentViewModel.StudentViewModel;


public class AddFragment extends Fragment {
    private Button button;
    private EditText editTextName,editTextAge;
    private StudentViewModel studentViewModel;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button = requireActivity().findViewById(R.id.button);
        editTextAge = requireActivity().findViewById(R.id.edit_age);
        editTextName = requireActivity().findViewById(R.id.edit_name);
        studentViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(StudentViewModel.class);
        button.setEnabled(false);
        editTextName.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextName,0);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String studentName = editTextName.getText().toString().trim();
                String studentAge = editTextAge.getText().toString().trim();
                button.setEnabled(!studentName.isEmpty() && !studentAge.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        editTextName.addTextChangedListener(textWatcher);
        editTextAge.addTextChangedListener(textWatcher);
        button.setOnClickListener(view -> {
            String studentName = editTextName.getText().toString().trim();
            String studentAge = editTextAge.getText().toString().trim();
            Student student = new Student(studentName,studentAge);
            studentViewModel.insertStudent(student);
            NavController navController = Navigation.findNavController(view);
            navController.navigateUp();

        });
    }
}