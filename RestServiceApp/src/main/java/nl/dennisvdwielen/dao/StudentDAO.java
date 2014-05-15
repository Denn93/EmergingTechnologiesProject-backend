package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.interfaces.ADatabaseHandler;
import nl.dennisvdwielen.pojo.Container;
import nl.dennisvdwielen.pojo.Student;
import nl.dennisvdwielen.factory.DatabaseFactory;
import nl.dennisvdwielen.factory.DatabaseFactory.DatabaseType;
import nl.dennisvdwielen.interfaces.IDao;

import java.util.ArrayList;

/**
 * Created by Dennis on 25-4-2014 at 16:37)
 *
 * This code is part of the ${PROJECT_NAME} project.
 * This class is within package ${PACKAGE_NAME}
 */
public class StudentDAO implements IDao<Container>{

    private ArrayList<Student> students = new ArrayList<Student>();

    public void addStudents() {
        students.add(new Student(4, "Dennis", "Wielen", 5, "Netherlands"));
        students.add(new Student(4, "Tim", "Timmetje", 5, "Uk"));
        students.add(new Student(4, "Axel", "Axeline", 5, "Netherlands"));
        students.add(new Student(4, "Jan", "Jansem", 5, "England"));
        students.add(new Student(4, "Ramon", "Esteve", 5, "Spain"));
        students.add(new Student(4, "Brian", "Jansen", 5, "Netherlands"));
        students.add(new Student(4, "Piet", "Pietersen", 5, "Netherlands"));
        students.add(new Student(4, "Puk", "Aaaa", 5, "Russia"));
    }


    @Override
    public ArrayList<Container> get(int id) {
        //addStudents();

        ADatabaseHandler handler =  new DatabaseFactory().getDatabaseHandler(DatabaseType.Mysql);
        Container container = handler.select(Container.class);

        ArrayList<Container> result = new ArrayList<Container>();
        result.add(container);

        return result;
    }

    @Override
    public boolean add(Container student) {
        return false;
    }

    @Override
    public boolean update(Container student) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
