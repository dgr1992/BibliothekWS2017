package at.fhv.team05.persistence;

import at.fhv.team05.domain.Category;

public class CategoryRepository extends Repository<Category> {

    @Override
    protected Class<Category> getModelClass() {
        return Category.class;
    }
}
