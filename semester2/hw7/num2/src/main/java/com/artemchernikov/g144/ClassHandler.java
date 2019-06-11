package com.artemchernikov.g144;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**A class describing handler of classes*/
public class ClassHandler {

    private final String directory;
    private Set<String> importSet = new HashSet<>();
    private Set<String> innerClasses = new HashSet<>();
    private StringBuilder classRealization = new StringBuilder();

    public ClassHandler() {
        directory = System.getProperty("user.dir") + "\\src\\main\\java\\com\\artemchernikov\\g144\\ResultsOfHandling";
    }

    /**A method returns string with all classes which are needed to import*/
    private String getImportsString() {
        return importSet.stream()
                .filter(s -> !innerClasses.contains(s))
                .map(s -> "import " + s + ";\n")
                .reduce((s1, s2) -> s1 + s2)
                .orElse("");
    }

    /**A method returns correct name of received class regardless of its generic-essential*/
    private String getCorrectName(Class<?> someClass) {
        String result = someClass.getSimpleName();
        if (someClass.getTypeParameters().length != 0) {
            result += Arrays.stream(someClass.getTypeParameters())
                    .map(TypeVariable::getName)
                    .collect(Collectors.joining(", ", "<", ">"));
        }
        return result;
    }

    /**
     * A method creates .java file with class which has the same fields and methods as the received one
     * @param someClass class, a copy of which we need to create
     * @throws IOException if the file writing was incorrect
     * */
    public void printStructure(Class<?> someClass) throws IOException {
        String fileName = directory + "\\" + someClass.getSimpleName() + ".java";
        try (FileWriter fout = new FileWriter(fileName, false)) {
            String classPackage = ClassHandler.class.getPackage() + ".ResultsOfHandling;\n\n";
            String classString = getClassString(someClass);
            importSet.remove(someClass.getCanonicalName());
            String classImports = getImportsString();

            fout.write(classPackage + classImports + classString);
        }
    }

    /**A method returns name and body of received class*/
    private String getClassString(Class<?> someClass) {
        for (Class<?> i : someClass.getInterfaces()) {
            importSet.add(i.getCanonicalName());
        }
        if (someClass.getSuperclass() != null && someClass.getSuperclass() != Object.class) {
            importSet.add(someClass.getSuperclass().getCanonicalName());
        }

        classRealization.append("\n");
        printNameWithSuperclassAndInterfaces(someClass);
        classRealization.append(" {\n");
        printFields(someClass);
        classRealization.append("\n");
        printMethods(someClass);

        printInnerClasses(someClass);
        classRealization.append("\n}");

        return classRealization.toString();
    }

    /**A method prints name, superclass and interfaces of received class*/
    private void printNameWithSuperclassAndInterfaces(Class<?> someClass) {
        classRealization.append(Modifier.toString(someClass.getModifiers())).append(" class ").append(someClass.getSimpleName());
        if (someClass.getTypeParameters().length != 0) {
            classRealization.append(Arrays.stream(someClass.getTypeParameters())
                    .map(TypeVariable::getName)
                    .collect(Collectors.joining(", ", "<", ">")));
        }

        if (someClass.getSuperclass() != null && someClass.getSuperclass() != Object.class) {
            classRealization.append(" extends ");
            classRealization.append(getCorrectName(someClass.getSuperclass()));
        }

        if (someClass.getInterfaces().length != 0) {
            classRealization.append(Arrays.stream(someClass.getInterfaces())
                    .map(this::getCorrectName)
                    .collect(Collectors.joining(", ", " implements ", "")));
        }
    }

    /**A method prints inner classes of received class*/
    private void printInnerClasses(Class<?> someClass) {
        for (Class<?> c : someClass.getDeclaredClasses()) {
            innerClasses.add(c.getCanonicalName());
            innerClasses.add(c.getSuperclass().getCanonicalName());
            for (Class<?> i : c.getInterfaces()) {
                innerClasses.add(i.getCanonicalName());
            }

            getClassString(c);
            classRealization.append("\n");
        }
    }

    /**A method prints all the declared fields of received class*/
    private void printFields(Class<?> someClass) {
        for (Field f : someClass.getDeclaredFields()) {
            if (!f.getType().isPrimitive() && !f.getType().isArray()) {
                importSet.add(f.getType().getCanonicalName());
            }

            classRealization.append(transformToString(f));
        }
    }

    /**A method prints all the declared methods of received class*/
    private void printMethods(Class<?> someClass) {
        for (Method m : someClass.getDeclaredMethods()) {
            if (!m.getReturnType().isPrimitive() && !m.getReturnType().isArray()) {
                importSet.add(m.getReturnType().getCanonicalName());
            }

            classRealization.append(transformToString(m));

            if (m.getExceptionTypes().length != 0) {
                Arrays.stream(m.getExceptionTypes()).forEach(exc -> importSet.add(exc.getCanonicalName()));
            }
        }
    }

    /**A method receives field and transforms it to appropriate string*/
    private String transformToString(Field field) {
        StringBuilder result = new StringBuilder("    " +
                Modifier.toString(field.getModifiers()) +
                " " +
                field.getType().getSimpleName() +
                " " +
                field.getName() +
                " = ");

        if (field.getType().isPrimitive()) {
            if (field.getType().getName().equals("boolean")) {
                result.append("false");
            } else {
                result.append("0");
            }
        } else {
            result.append("null");
        }

        result.append(";\n");
        return result.toString();
    }

    /**A method receives method and transforms it to appropriate string*/
    private String transformToString(Method method) {
        StringBuilder result = new StringBuilder("    " +
                Modifier.toString(method.getModifiers()) +
                " " +
                method.getReturnType().getSimpleName() +
                " " +
                method.getName());

        result.append(Arrays.stream(method.getParameters())
                .map(p -> p.getParameterizedType().getTypeName() + " " + p.getName())
                .collect(Collectors.joining(", ", "(", ")")));

        if (method.getExceptionTypes().length != 0) {
            result.append(Arrays.stream(method.getExceptionTypes())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", ", "throws ", " ")));
        }

        result.append("{\n");

        if (!method.getReturnType().getSimpleName().equals("void")) {
            result.append("        return ");
            if (!method.getReturnType().isPrimitive()) {
                result.append(method.getDefaultValue());
            } else {
                if (method.getReturnType().getSimpleName().equals("boolean")) {
                    result.append("false");
                } else {
                    result.append("0");
                }
            }
            result.append(";\n");
        }

        result.append("    }\n\n");

        return result.toString();
    }

    /**A method returns string with elements of received lists, except those ones, which exist in both lists*/
    private String getStringOfDifferentMembers(List<String> firstClassMembers, List<String> secondClassMembers) {
        Set<String> allMethods = new HashSet<>();
        allMethods.addAll(firstClassMembers);
        allMethods.addAll(secondClassMembers);

        Set<String> sameMethods = new HashSet<>();
        for (String s : firstClassMembers) {
            if (secondClassMembers.contains(s)) {
                sameMethods.add(s);
            }
        }

        StringBuilder result = new StringBuilder();

        for (String s : allMethods) {
            if (!sameMethods.contains(s)) {
                result.append(s);
            }
        }

        return result.toString();
    }

    /**
     * A method prints fields and methods of classes, except those ones, which exist in both classes
     * @param firstClass first class to compare
     * @param secondClass second class to compare
     * @return true if classes are equal and false in otherwise
     * */
    public boolean diffClasses(Class<?> firstClass, Class<?> secondClass) {
        List<String> firstClassFields = Arrays.stream(firstClass.getDeclaredMethods())
                .map(this::transformToString)
                .collect(Collectors.toList());
        List<String> secondClassFields = Arrays.stream(secondClass.getDeclaredMethods())
                .map(this::transformToString)
                .collect(Collectors.toList());

        List<String> firstClassMethods = Arrays.stream(firstClass.getDeclaredMethods())
                .map(m -> transformToString(m).replaceAll(" extends java.lang.Object", ""))
                .collect(Collectors.toList());
        List<String> secondClassMethods = Arrays.stream(secondClass.getDeclaredMethods())
                .map(m -> transformToString(m).replaceAll(" extends java.lang.Object", ""))
                .collect(Collectors.toList());

        String result = getStringOfDifferentMembers(firstClassFields, secondClassFields) +
                getStringOfDifferentMembers(firstClassMethods, secondClassMethods);

        System.out.println(result);

        return result.isEmpty();
    }
}
