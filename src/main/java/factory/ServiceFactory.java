package factory;

import db.*;
import model.service.CategoryService;
import model.service.TaskService;
import model.service.UserService;

public class ServiceFactory {

    private static final CategoryDao CATEGORY_DAO = new CategoryDaoImpl();
    public static final CategoryService CATEGORY_SERVICE = new CategoryService(getCategoryDao());
    private static final TaskDao taskDao = new TaskDaoImpl();
    private static final TaskService taskService = new TaskService(getTaskDao());

    private static final UserDao userDao = new UserDaoImpl();
    private static final UserService USER_SERVICE = new UserService(getUserDao());

    private ServiceFactory() {
    }

    public static TaskService getTaskService() {
        return taskService;
    }

    public static TaskDao getTaskDao() {
        return taskDao;
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static CategoryDao getCategoryDao() {
        return CATEGORY_DAO;
    }

    public static CategoryService getCategoryService() {
        return CATEGORY_SERVICE;
    }


}
