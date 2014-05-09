package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.inferface.IDao;

/**
 * Created by Dennis on 26-4-2014 at 16:33
 */
public class DaoFactory {

    public IDao getDAO(Class className) {

        IDao result = null;

        String name = className.getSimpleName();
        try {
            Object dao = Class.forName("nl.dennisvdwielen.dao." + name + "DAO").newInstance();
            result = (IDao) dao;

        } catch (ClassNotFoundException e) {

        } catch (IllegalAccessException e) {

        } catch (InstantiationException e) {

        }

        return result;
    }
}
