package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.dao.StudentDAO;
import nl.dennisvdwielen.inferface.IDao;

import java.util.IdentityHashMap;

/**
 * Created by Dennis on 26-4-2014 at 16:33)
 *
 * This code is part of the ${PROJECT_NAME} project.
 * This class is within package ${PACKAGE_NAME}
 */
public class DaoFactory {

    public IDao getDAO(Class className) {




        return new StudentDAO();
    }
}
