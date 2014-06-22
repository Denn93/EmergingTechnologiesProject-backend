package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.abstracts.ADao;

/**
 * Created by Dennis on 26-4-2014 at 16:33
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */

/**
 * This factory class creates a DAO object bases on the className given Thus, builds DAO classes
 */
public class DaoFactory {

    public ADao getDAO(Class className) {

        ADao result = null;

        String name = className.getSimpleName();
        try {
            Object dao = Class.forName("nl.dennisvdwielen.dao." + name + "DAO").newInstance();
            result = (ADao) dao;

        } catch (ClassNotFoundException e) {
            //TODO Add Useful Error Message
        } catch (IllegalAccessException e) {
            //TODO Add Useful Error Message
        } catch (InstantiationException e) {
            //TODO Add Useful Error Message
        }

        return result;
    }
}
