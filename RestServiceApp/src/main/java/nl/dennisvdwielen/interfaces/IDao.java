package nl.dennisvdwielen.interfaces;

import java.util.ArrayList;

/**
 * Created by Dennis on 26-4-2014.
 */
public interface IDao<Dto> {

    public ArrayList<Dto> get(int id);

    public boolean add(Dto dto);

    public boolean update(Dto dto);

    public boolean delete();

}
