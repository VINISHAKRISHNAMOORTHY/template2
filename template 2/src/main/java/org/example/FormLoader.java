package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.*;

public class FormLoader {

    public static List<Map<String, Object>> loadFormFields(String xmlPath) {
        List<Map<String, Object>> formFields = new ArrayList<>();

        try {
            InputStream xmlInput = FormLoader.class.getResourceAsStream(xmlPath);
            if (xmlInput == null) {
                System.out.println("XML file not found.");
                return formFields;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlInput);

            NodeList inputs = document.getElementsByTagName("input");

            for (int i = 0; i < inputs.getLength(); i++) {
                Element input = (Element) inputs.item(i);
                Map<String, Object> field = new HashMap<>();
                field.put("key", getText(input, "key"));
                field.put("kind", getText(input, "kind"));
                field.put("title", getText(input, "title"));

                if ("radio".equals(field.get("kind"))) {
                    List<String> options = new ArrayList<>();
                    NodeList optionNodes = input.getElementsByTagName("option");
                    for (int j = 0; j < optionNodes.getLength(); j++) {
                        options.add(optionNodes.item(j).getTextContent());
                    }
                    field.put("choices", options);
                }

                formFields.add(field);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formFields;
    }

    private static String getText(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        return nodes.getLength() > 0 ? nodes.item(0).getTextContent() : "";
    }
}