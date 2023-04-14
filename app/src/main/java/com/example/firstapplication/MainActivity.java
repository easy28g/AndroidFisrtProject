package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firstapplication.adapters.CategoryAdapter;
import com.example.firstapplication.adapters.CourseAdapter;
import com.example.firstapplication.models.Category;
import com.example.firstapplication.models.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, coursesRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Игры"));
        categoryList.add(new Category(2, "Сайты"));
        categoryList.add(new Category(3, "Языки"));
        categoryList.add(new Category(4, "Прочее"));
        setCategoryRecycler(categoryList);


        courseList.add(new Course(1, "java", "Профессия Java \n разработчик", "1 января", "начальный", "#424345", "Test", 3));
        courseList.add(new Course(2, "python", "Профессия Python \n разработчик", "10 января", "начальный", "#424345", "Test", 3));
        courseList.add(new Course(3, "cpp", "Профессия C++ \n разработчик", "15 января", "начальный", "#424345", "Test",3));
        courseList.add(new Course(4, "front_end", "Профессия Front-end \n разработчик", "1 февраля", "начальный", "#424345", "Test", 2));
        courseList.add(new Course(5, "full_stack", "Профессия Full Stack \n разработчик", "5 февраля", "начальный", "#424345", "Test",2));
        courseList.add(new Course(6, "unity", "Профессия Unity 3d \n разработчик", "10 февраля", "начальный", "#424345", "Test",1));
        setCourseRecycler(courseList);

        fullCourseList.addAll(courseList);
    }

    public void openShoppinCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        coursesRecycler = findViewById(R.id.coursesRecycler);
        coursesRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        coursesRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullCourseList);

        List<Course> filterCourses = new ArrayList<>();

        for(Course c : courseList){
            if(c.getCategory() == category){
                filterCourses.add(c);
            }
        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }
}