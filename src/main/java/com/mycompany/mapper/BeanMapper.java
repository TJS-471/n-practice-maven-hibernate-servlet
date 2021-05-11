package main.java.com.mycompany.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that is used for mapping dissimilar Java classes with similar attribute names.
 * @author Julia Tsukanova
 * @version 1.0
 */
public final class BeanMapper {
    private static BeanMapper beanMapper;
    private static Mapper mapper;

    private BeanMapper() {
        mapper = new DozerBeanMapper();
    }

    public static synchronized BeanMapper getInstance() {
        if (beanMapper == null) {
            beanMapper = new BeanMapper();
        }
        return beanMapper;
    }

    public static <T> T singleMapper(Object from, Class<T> toClass) {
        return mapper.map(from, toClass);
    }

    public static <E, T> List<T> listMapToList(Iterable<E> iterable, Class<T> toClass) {
        List<T> list = new ArrayList<>();
        for (E e : iterable) {
            list.add(mapper.map(e, toClass));
        }
        return list;
    }
}
