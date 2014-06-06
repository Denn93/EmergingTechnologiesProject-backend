package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.abstracts.ADao;

/**
 * Created by Dennis on 26-4-2014 at 16:33
 */
public class DaoFactory {

    public ADao getDAO(Class className) {

        ADao result = null;

        String name = className.getSimpleName();
        try {
            Object dao = Class.forName("nl.dennisvdwielen.dao." + name + "DAO").newInstance();
            result = (ADao) dao;

        } catch (ClassNotFoundException e) {
            //TODO Add Usefull Error Message
        } catch (IllegalAccessException e) {
            //TODO Add Usefull Error Message
        } catch (InstantiationException e) {
            //TODO Add Usefull Error Message
        }

        return result;
    }
}
