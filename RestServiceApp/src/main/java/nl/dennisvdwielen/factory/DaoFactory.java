package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.interfaces.ADao;

/**
 * Created by Dennis on 26-4-2014 at 16:33
 */
public class DaoFactory {

    public ADao getDAO(Class className) {

        ADao result = null;

        String name = className.getSimpleName();
        try{
            Object dao = Class.forName("nl.dennisvdwielen.dao." + name + "DAO").newInstance();
            result = (ADao) dao;

        }catch (ClassNotFoundException e) {

        }catch (IllegalAccessException e) {

        }catch (InstantiationException e) {

        }

        return result;
    }
}
