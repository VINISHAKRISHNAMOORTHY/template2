package org.example;

import org.apache.velocity.app.VelocityEngine;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Map<String, Object>> formFields = FormLoader.loadFormFields("/page.xml");

        if (formFields.isEmpty()) {
            System.out.println("No form fields found.");
            return;
        }

        VelocityEngine engine = VelocityConfig.initializeVelocity();
        FormRenderer.renderFormSteps(engine, formFields);
    }
}
