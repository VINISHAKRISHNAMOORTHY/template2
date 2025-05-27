package org.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.*;

public class FormRenderer {

    public static void renderFormSteps(VelocityEngine engine, List<Map<String, Object>> fields) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> responses = new HashMap<>();

        for (Map<String, Object> field : fields) {
            Template template = engine.getTemplate("templates/form.vm");

            VelocityContext context = new VelocityContext();
            context.put("input", field);

            StringWriter output = new StringWriter();
            template.merge(context, output);
            System.out.println(output);

            if ("message".equals(field.get("kind"))) {
                break;
            }

            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            responses.put((String) field.get("key"), answer);
        }

        System.out.println("Form complete. Thank you!");
    }
}
