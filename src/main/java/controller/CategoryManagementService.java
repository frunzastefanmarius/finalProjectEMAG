package controller;

import db.DbCategoryOperations;
import entity.CategoryDisplay;

import java.util.List;

public class CategoryManagementService {

    public List<CategoryDisplay> showAllCategories() {

        DbCategoryOperations db = new DbCategoryOperations();
        List<CategoryDisplay> lc = db.readAllCategory();
        return lc;

    }
}
