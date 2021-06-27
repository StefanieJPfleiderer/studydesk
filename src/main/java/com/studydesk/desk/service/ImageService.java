package com.studydesk.desk.service;

import com.studydesk.desk.entity.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
public class ImageService {

    public <T> ArrayList<T> getBase64String(final ArrayList<T> elements) {
        for (T element : elements) {
            Category category = (Category) element;
            category.setBase64String(Base64.getEncoder().encodeToString(category.getImage()));
        }
        return elements;
    }
}
