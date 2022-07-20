package model.service;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    @Test
    public void testService() {
        CategoryService categoryService = mock(CategoryService.class);
        when(categoryService.requestForAddCategoryIsValid("nameEng", "nameRu")).thenReturn(true);
        Assert.assertEquals(categoryService.requestForAddCategoryIsValid("nameEng","nameRu"),true);
        when(categoryService.requestForAddCategoryIsValid("en", "nameRu")).thenReturn(false);
        Assert.assertEquals(categoryService.requestForAddCategoryIsValid("en", "nameRu"),false);

    }
}
